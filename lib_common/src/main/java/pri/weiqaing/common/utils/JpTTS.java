package pri.weiqaing.common.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import pri.weiqaing.common.base.BaseApplication;

public class JpTTS {

    public static final String KEY_PARAM_SPEAKER = "speaker";
    private static int currentId = 0;
    private static Speech currentSpeech;
    private static MediaPlayer currentPlayer;
    private static JpTTS instance = null;
    private Context context;
    private Handler handler = new Handler();
    private MediaPlayer player;
    private State state = State.IDLE;
    private TextToSpeech.OnUtteranceCompletedListener onUtteranceCompleted;
    private OnStateChangedListener onStateChanged;
    private OnErrorListener onError;
    private OnCompletionListener onComplete = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            log("completed");
            if (currentSpeech == null) return;
            String id = getUtteranceId(currentSpeech);
            setState(State.IDLE, currentSpeech);
            currentSpeech = null;
            if (onUtteranceCompleted != null) {
                onUtteranceCompleted.onUtteranceCompleted(id);
            }
        }
    };

    public JpTTS(TextToSpeech.OnInitListener listener) {
        this.context = BaseApplication.getInstance();
        this.player = new MediaPlayer();
        player.setOnCompletionListener(onComplete);
        if (listener != null) {
            listener.onInit(TextToSpeech.SUCCESS);
        }
    }

    public static synchronized JpTTS getInstance(TextToSpeech.OnInitListener listener) {

        if (instance == null) {
            instance = new JpTTS(listener);
        }
        return instance;
    }

    private static String getUtteranceId(Speech speech) {
        if (speech != null) {
            return speech.params().get(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
        } else {
            return null;
        }
    }

    private static void log(String message) {
        Log.i("jatts", message);
    }

    public boolean isSpeaking() {
        return state != State.IDLE;
    }

    public void setOnUtteranceCompletedListener(
            TextToSpeech.OnUtteranceCompletedListener listener) {
        onUtteranceCompleted = listener;
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        onStateChanged = listener;
    }

    public void setOnErrorListener(OnErrorListener listener) {
        onError = listener;
    }

    public synchronized void speak(
            String text, int queueMode, HashMap<String, String> params) {
        if (queueMode == TextToSpeech.QUEUE_ADD) {
            throw new RuntimeException("queueMode == QUEUE_ADD is not implemented");
        }
        stop();
        int id = ++currentId;
        currentSpeech = new Speech(text, params, id, null);
        currentSpeech.start();
    }

    public void stop() {
        if (currentSpeech != null) currentSpeech.interrupt();
        if (currentPlayer != null) currentPlayer.stop();
    }

    public int synthesizeToFile(String text, HashMap<String, String> params, String filename) {
        try {
            new Speech(text, params, 0, filename).download();
            return TextToSpeech.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return TextToSpeech.ERROR;
        }
    }

    public State getState() {
        return state;
    }

    private void setState(final State state, final Speech speech) {
        if (speech != null && speech != currentSpeech) return;
        this.state = state;
        if (onStateChanged != null) {
            handler.post(new Runnable() {
                public void run() {
                    onStateChanged.onStateChanged(state, getUtteranceId(speech));
                }
            });
        }
    }

    private void notifyError(final Exception exception, final Speech speech) {
        exception.printStackTrace();
        setState(State.IDLE, speech);
        if (onError != null) {
            handler.post(new Runnable() {
                public void run() {
                    onError.onError(exception, getUtteranceId(speech));
                }
            });
        }
    }

    public enum State {
        IDLE, LOADING, SPEAKING,
    }

    public interface OnStateChangedListener {
        void onStateChanged(State state, String utteranceId);
    }

    public interface OnErrorListener {
        void onError(Exception exception, String utteranceId);
    }

    private class Speech extends Thread {

        private final String TEMP_FILE_NAME = "jatts.temp.mp3";
        private String text;
        private HashMap<String, String> params;
        private int id;
        private String localPath;
        private FileOutputStream localFile;

        Speech(String text, HashMap<String, String> params, int id, String localPath) {
            this.text = text;
            this.params = params != null ? params : new HashMap<String, String>();
            this.id = id;
            try {
                if (localPath == null) {
                    this.localPath = context.getFilesDir() + "/" + TEMP_FILE_NAME;
                    // To prevent thread for previous speech (which may be still running)
                    // from writing to the same file.
                    context.deleteFile(TEMP_FILE_NAME);
                    // It seems we need to make it world readable to let MediaPlayer
                    // play it.
                    this.localFile = context.openFileOutput(
                            TEMP_FILE_NAME, Context.MODE_PRIVATE);
                } else {
                    this.localPath = localPath;
                    this.localFile = new FileOutputStream(localPath);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void start() {
            setState(JpTTS.State.LOADING, this);
            super.start();
        }

        @Override
        public void run() {
            try {
                download();
                play();
            } catch (final Exception e) {
                notifyError(e, this);
            }
        }

        public void download() throws IOException, InterruptedException {

            URL url = getUrl();
            log("connecting to " + url.toString());
            HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(true);
            urlconn.connect();

            if (urlconn.getResponseCode() < 200 || urlconn.getResponseCode() >= 300) {
                throw new RuntimeException(String.format("%03d %s",
                        urlconn.getResponseCode(), urlconn.getResponseMessage()));
            }

            log("loading " + url.toString());
            InputStream in = urlconn.getInputStream();
            int read;
            byte[] buffer = new byte[4096];
            while ((read = in.read(buffer)) > 0) {
                localFile.write(buffer, 0, read);
            }
            localFile.close();
            in.close();
            urlconn.disconnect();

        }

        private void play() {
            handler.post(new Runnable() {
                public void run() {
                    if (id != currentId) return;
                    log("speaking " + localPath);
                    setState(JpTTS.State.SPEAKING, Speech.this);
                    currentPlayer = player;
                    player.reset();
                    player.setAudioStreamType(getStreamType());
                    try {
                        player.setDataSource(localPath);
                        player.prepare();
                    } catch (IllegalArgumentException e) {
                        notifyError(e, Speech.this);
                        return;
                    } catch (IllegalStateException e) {
                        notifyError(e, Speech.this);
                        return;
                    } catch (IOException e) {
                        notifyError(e, Speech.this);
                        return;
                    }
                    player.start();
                }
            });
        }

        private URL getUrl() {
            String url = "http://gimite.net/speech?format=mp3&text=" + Uri.encode(text);
            for (Map.Entry<String, String> en : params.entrySet()) {
                url += "&" + en.getKey() + "=" + Uri.encode(en.getValue());
            }
            try {
                return new URL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        private int getStreamType() {
            String streamStr = params.get(TextToSpeech.Engine.KEY_PARAM_STREAM);
            if (streamStr != null) {
                return Integer.parseInt(streamStr);
            } else {
                return TextToSpeech.Engine.DEFAULT_STREAM;
            }
        }

        public HashMap<String, String> params() {
            return params;
        }

    }

}

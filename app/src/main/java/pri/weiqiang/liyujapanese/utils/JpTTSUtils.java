package pri.weiqiang.liyujapanese.utils;

import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;

import java.util.HashMap;

import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.config.Constants;
import pri.weiqiang.liyujapanese.manager.SharedPreferenceManager;
import pri.weiqiang.liyujapanese.rxbus.RxBus;
import pri.weiqiang.liyujapanese.rxbus.event.EventContainer;
import pri.weiqiang.liyujapanese.rxbus.event.SettingEvent;
import pri.weiqiang.liyujapanese.utils.JpTTS.State;

public class JpTTSUtils {

    private static final String TAG = JpTTSUtils.class.getSimpleName();
    public static String tts_type = SharedPreferenceManager.getInstance().getString(Constants.TTS_TYPE, Constants.TTS_MALE_01);
    private static JpTTSUtils instance = null;
    private JpTTS tts;
    ;
    private JpTTS.OnStateChangedListener onStateChanged =
            new JpTTS.OnStateChangedListener() {
                public void onStateChanged(State state, String utteranceId) {
                    if (state == State.LOADING) {
                        Log.e(TAG, "LOADING");
                    } else if (state == State.SPEAKING) {
                        Log.e(TAG, "SPEAKING");
                    }
                }
            };
    private OnUtteranceCompletedListener onTtsComplete = new OnUtteranceCompletedListener() {
        public void onUtteranceCompleted(String utteranceId) {
            Log.e(TAG, "onUtteranceCompleted");

        }
    };
    private JpTTS.OnErrorListener onError =
            new JpTTS.OnErrorListener() {
                public void onError(Exception exception, String utteranceId) {
                    Log.e(TAG, (String.format("Exception[: %s", exception.toString())));
                    RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_SETTING, new SettingEvent(R.string.tts_fail)));
                }
            };

    public static synchronized JpTTSUtils getInstance() {
        if (instance == null) {
            instance = new JpTTSUtils();
        }
        return instance;
    }

    public void updateTtsType() {
        tts_type = SharedPreferenceManager.getInstance().getString(Constants.TTS_TYPE, Constants.TTS_MALE_01);
        ;
    }

    public void SpeakJP(String phonetic) {
        tts = JpTTS.getInstance(null);
        tts.setOnStateChangedListener(onStateChanged);
        tts.setOnUtteranceCompletedListener(onTtsComplete);
        tts.setOnErrorListener(onError);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_MUSIC));
        params.put(JpTTS.KEY_PARAM_SPEAKER, tts_type);
        tts.speak(phonetic, TextToSpeech.QUEUE_FLUSH, params);
    }

    private void saveToFile() {
        final String text = null;
        new Thread() {
            public void run() {
                if (tts.synthesizeToFile(text, null, "/sdcard/saved.mp3") ==
                        TextToSpeech.SUCCESS) {
                    Log.e(TAG, "save SUCCESS!");
                }
            }
        }.start();
    }

}
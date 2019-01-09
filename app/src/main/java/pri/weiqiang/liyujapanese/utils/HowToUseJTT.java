package pri.weiqiang.liyujapanese.utils;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

import pri.weiqiang.liyujapanese.utils.JpTTS.State;

public class HowToUseJTT extends Activity {

    private EditText inputEdit;
    private Button speakButton;
    private TextView stateText;
    private Spinner speakerSpinner;
    private JpTTS tts;
    private OnClickListener onSpeakButtonClick = new OnClickListener() {
        public void onClick(View v) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    String.valueOf(AudioManager.STREAM_MUSIC));
            params.put(JpTTS.KEY_PARAM_SPEAKER,
                    (String) speakerSpinner.getSelectedItem());
            tts.speak(inputEdit.getText().toString(), TextToSpeech.QUEUE_FLUSH, params);
        }
    };
    private JpTTS.OnStateChangedListener onStateChanged =
            new JpTTS.OnStateChangedListener() {
                public void onStateChanged(State state, String utteranceId) {
                    if (state == JpTTS.State.LOADING) {
                        stateText.setText("LOADING");
                    } else if (state == JpTTS.State.SPEAKING) {
                        stateText.setText("SPEAKING");
                    }
                }
            };
    private OnUtteranceCompletedListener onTtsComplete = new OnUtteranceCompletedListener() {
        public void onUtteranceCompleted(String utteranceId) {
            stateText.setText("");
        }
    };
    private JpTTS.OnErrorListener onError =
            new JpTTS.OnErrorListener() {
                public void onError(Exception exception, String utteranceId) {
                    stateText.setText(String.format("Exception[: %s", exception.toString()));
                }
            };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
//        setContentView(R.layout.main);

        tts = new JpTTS(null);
        tts.setOnStateChangedListener(onStateChanged);
        tts.setOnUtteranceCompletedListener(onTtsComplete);
        tts.setOnErrorListener(onError);

//        inputEdit = (EditText)findViewById(R.id.inputEdit);
//        speakButton = (Button)findViewById(R.id.speakButton);
//        speakButton.setOnClickListener(onSpeakButtonClick);
//        stateText = (TextView)findViewById(R.id.stateText);
//        speakerSpinner = (Spinner)findViewById(R.id.speakerSpinner);

        String[] speakers = {"male01", "female01", "male02"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, speakers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speakerSpinner.setAdapter(adapter);
        speakerSpinner.setSelection(0);
    }

    private void saveToFile() {
        final String text = inputEdit.getText().toString();
        new Thread() {
            public void run() {
                if (tts.synthesizeToFile(text, null, "/sdcard/saved.mp3") ==
                        TextToSpeech.SUCCESS) {
                    stateText.setText("SUCCESS");
                }
            }
        }.start();
    }

}
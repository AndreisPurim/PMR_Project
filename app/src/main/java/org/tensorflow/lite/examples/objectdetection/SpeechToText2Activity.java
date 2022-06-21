package org.tensorflow.lite.examples.objectdetection;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;
import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;
import org.vosk.android.RecognitionListener;
import org.vosk.android.SpeechService;
import org.vosk.android.StorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SpeechToText2Activity extends Activity implements RecognitionListener {

    private final String cat = "SPEECH_ACTIVITY";
    private TextView resultView;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private Model model;
    private SpeechService speechService;
    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private Queue<String> queue;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_speeck_to_text2);

        resultView = findViewById(R.id.result_text);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        queue = new LinkedList<>();

        findViewById(R.id.recognize_mic).setOnClickListener(view -> recognizeMicrophone());
        ((ToggleButton) findViewById(R.id.pause)).setOnCheckedChangeListener((view, isChecked) -> pause(isChecked));
        LibVosk.setLogLevel(LogLevel.INFO);

        // Check if user has given permission to record audio, init the model after permission is granted
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
        } else {
            initModel();
        }
    }

    private void recognizeMicrophone() {
        if (speechService != null) {
            speechService.stop();
            speechService = null;
        } else {
            try {
                Recognizer rec = new Recognizer(model, 16000.0f);
                speechService = new SpeechService(rec, 16000.0f);
                speechService.startListening(this);
            } catch (IOException e) {

            }
        }
    }

    private void initModel() {
        StorageService.unpack(this, "model-en-us", "model",
                (model) -> {
                    this.model = model;
                    resultView.setText(R.string.ready);
                    ((Button) findViewById(R.id.recognize_mic)).setText(R.string.recognize_mic);
                    findViewById(R.id.recognize_file).setEnabled(true);
                    findViewById(R.id.recognize_mic).setEnabled(true);
                    findViewById(R.id.pause).setEnabled((false));
                },
                (exception) -> setErrorState("Failed to unpack the model " + exception.getMessage()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Recognizer initialization is a time-consuming and it involves IO,
                // so we execute it in async task
                initModel();
            } else {
                finish();
            }
        }
    }

    @Override
    public void onPartialResult(String hypothesis) {

        try {
            JSONObject json = new JSONObject(hypothesis);
            Log.i(cat, "Partial Result: <"+json.getString("partial") + ">");
            //resultView.append("Partial Result: "+json.getString("partial") + "\n");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResult(String hypothesis) {
        try {
            JSONObject json = new JSONObject(hypothesis);
            String text = json.getString("text");
            Log.i(cat, "onResult: <"+ text + ">");
            if(queue.size() > 3){
                queue.remove();
            }
            queue.add(text);
            List list = new ArrayList<String>();
            for(String el : queue){
                list.add(el);
            }

            txt1.setText(list.get(0).toString());
            if(list.size() > 1) {
                txt2.setText(list.get(1).toString());
            }
            if(list.size()>2){
                txt3.setText(list.get(2).toString());
            }

            //resultView.append("onResult: "+text + "\n");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinalResult(String hypothesis) {
        try {
            JSONObject json = new JSONObject(hypothesis);
            Log.i(cat, "onFinalResult: "+json + "\n");
            //resultView.append("onFinalResult: "+json + "\n");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Exception exception) {
        setErrorState(exception.getMessage());
    }

    @Override
    public void onTimeout() {
        return;
    }

    private void setErrorState(String message) {
        resultView.setText(message);
        ((Button) findViewById(R.id.recognize_mic)).setText(R.string.recognize_mic);
        findViewById(R.id.recognize_file).setEnabled(false);
        findViewById(R.id.recognize_mic).setEnabled(false);
    }

    private void pause(boolean checked) {
        if (speechService != null) {
            speechService.setPause(checked);
        }
    }
}
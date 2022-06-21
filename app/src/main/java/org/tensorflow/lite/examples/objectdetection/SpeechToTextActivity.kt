package org.tensorflow.lite.examples.objectdetection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.tensorflow.lite.examples.objectdetection.databinding.ActivitySpeechToTextBinding


class SpeechToTextActivity : AppCompatActivity() {

    var CAT: String = "SPEECH_ACTIVITY"
    private lateinit var activitySpeechToTextBinding: ActivitySpeechToTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySpeechToTextBinding = ActivitySpeechToTextBinding.inflate(layoutInflater)
        setContentView(activitySpeechToTextBinding.root)

        supportActionBar?.title = "Speech Activity"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.i(CAT, "going back")
        finish()
        return super.onSupportNavigateUp()
    }
}
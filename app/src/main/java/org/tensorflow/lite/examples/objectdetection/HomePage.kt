package org.tensorflow.lite.examples.objectdetection

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.Toast
import org.tensorflow.lite.examples.objectdetection.databinding.ActivityHomePageBinding

class HomePage : Activity() {
    private lateinit var activityHomePageBinding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomePageBinding= ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(activityHomePageBinding.root)
        //setContentView(R.layout.activity_home_page)

        val hearButton : Button = findViewById(R.id.hearButton)
        val viewButton : Button = findViewById(R.id.viewButton)

        hearButton.setOnClickListener {
            Toast.makeText(this, "Start to hear!", Toast.LENGTH_SHORT).show()
            // TODO:
            val toHear = Intent(this, SpeechToTextActivity::class.java)
            startActivity(toHear)

        }

        viewButton.setOnClickListener {
            Toast.makeText(this, "Start to see!", Toast.LENGTH_SHORT).show()
            // TODO:
            val toView = Intent(this, SeeActivity::class.java)
            startActivity(toView)
        }
    }


}
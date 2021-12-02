package com.example.lab8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab8.databinding.ActivityFeedbackBinding


class FeedbackActivity : AppCompatActivity()
{
    lateinit var animalLabel : TextView
    lateinit var feedbackEditText : EditText

    private var getAnimalText : String? = null

    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        animalLabel = findViewById(R.id.animalType)
        feedbackEditText = findViewById(R.id.feedbackEditText)

//        intent/ GRAB DATA
        getAnimalText = intent.getStringExtra("animalType")

        getAnimalText?.let{animalLabel.text = "What do you like about $getAnimalText"+ "s?" }



    }

    override fun onBackPressed() {
        val data = Intent()
        val message = feedbackEditText.text.toString()
        Log.i("H", "message:" + message)
        data.putExtra("message", message)
        setResult(RESULT_OK, data) //must be set before
        super.onBackPressed()
        finish()
    }


}
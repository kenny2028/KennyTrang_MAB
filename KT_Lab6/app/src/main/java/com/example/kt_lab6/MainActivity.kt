package com.example.kt_lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun snowButton(view: View) {
        val getEditText = findViewById<EditText>(R.id.editTextName)
        val name = getEditText.text
        val textLabel = findViewById<TextView>(R.id.textLabel)
        val imageBox =  findViewById<ImageView>(R.id.imageView)

        val select = (0..1).random()

        if (select == 0) {
            textLabel.text =  "It's Mariah Carey Season " + name + "!" + "\n" + "All I want for Christmas is yoouuuuu"
            imageBox.setImageResource(R.drawable.merrychristmas)
        } else {
            textLabel.text =  "It's Frank Sinatra Season " + name + "!" + "\n" + "Let it snow, let it snow, let it snow"
            imageBox.setImageResource(R.drawable.merrychristmas2)
        }




    }


}
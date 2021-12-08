package com.example.plankable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plankable.databinding.ActivityGameBinding

class gameActivity : AppCompatActivity() {


        private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.plankableNoActionBar)

    }


















    fun secondconvert(seconds: Int):String {
        var minutes = seconds/60
//        minutes = floor(minutes)
        var finalString = ""

        var newseconds = seconds % 60

        minutes = minutes.toInt()

        if(newseconds == 0) {
            var secondszero = "00"
            finalString = minutes.toString() + ":" + secondszero
        } else {
            finalString = minutes.toString() + ":" + newseconds.toString()
        }


//            return new string format

        return finalString
    }




}
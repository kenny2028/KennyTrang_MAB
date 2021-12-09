package com.example.plankable

import android.media.Image
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.plankable.databinding.ActivityGameBinding

class gameActivity : AppCompatActivity() {


    private lateinit var binding: ActivityGameBinding
    lateinit var bluebox: ImageView
    lateinit var greenbox : ImageView
    lateinit var redbox: ImageView
    lateinit var orangebox : ImageView
    lateinit var pinkbox : ImageView
    lateinit var yellowbox : ImageView
    lateinit var layoutroot: ConstraintLayout
    private var gamePaused: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            binding = ActivityGameBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setTheme(R.style.plankableNoActionBar)

            bluebox = findViewById(R.id.bluebox)
            greenbox = findViewById(R.id.greenbox)
            redbox = findViewById(R.id.redbox)
            orangebox = findViewById(R.id.orangebox)
            pinkbox = findViewById(R.id.pinkbox)
            yellowbox = findViewById(R.id.yellowbox)



            layoutroot = findViewById(R.id.root_layout)

        if (!gamePaused) {
            bluebox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> bluebox.setImageResource(R.drawable.box2)//Do Something
                        MotionEvent.ACTION_UP -> bluebox.setImageResource(R.drawable.box1)
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            greenbox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> greenbox.setImageResource(R.drawable.greenbox2)//Do Something
                        MotionEvent.ACTION_UP -> greenbox.setImageResource(R.drawable.greenbox1)
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            redbox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> redbox.setImageResource(R.drawable.redbox2)//Do Something
                        MotionEvent.ACTION_UP -> redbox.setImageResource(R.drawable.redbox1)
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            orangebox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> orangebox.setImageResource(R.drawable.orangebox2)//Do Something
                        MotionEvent.ACTION_UP -> orangebox.setImageResource(R.drawable.orangebox1)
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            yellowbox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> yellowbox.setImageResource(R.drawable.yellowbox2)//Do Something
                        MotionEvent.ACTION_UP -> yellowbox.setImageResource(R.drawable.yellowbox1)
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })

            pinkbox.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> pinkbox.setImageResource(R.drawable.pinkbox2)//Do Something
                        MotionEvent.ACTION_UP -> pinkbox.setImageResource(R.drawable.pinkbox1)
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })
        }









    }
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





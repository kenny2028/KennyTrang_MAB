package com.example.plankable

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    var highscoreSave = ""

    var timerval: Double = 0.00
    lateinit var layoutRoot : ConstraintLayout
    lateinit var switch : SwitchMaterial
    var switchchecker: Int = 0
    lateinit var switchLabel : TextView
    private val REQUEST_CODE = 1
    lateinit var beginbutton : Button
    private var exportTime = ""
    private var showTimer = true
    lateinit var scoreLabel : TextView
    lateinit var highscoreLabel : TextView
    private var highScoreTracker = 0

    private var position = 0

    private var highScore =  Array(20) { 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slider = findViewById<Slider>(R.id.slider)
        val layoutRoot = findViewById<ConstraintLayout>(R.id.root_layout)
        val timerlabel = findViewById<TextView>(R.id.timerlabel)
        val switch = findViewById<SwitchMaterial>(R.id.switch1)
        val switchLabel = findViewById<TextView>(R.id.switch_label)
        val beginbutton = findViewById<Button>(R.id.begin_button)

        highscoreLabel = findViewById(R.id.highscoreLabel)

        slider.addOnChangeListener { slider, value, fromUser ->
            timerlabel.text = secondconvert(value.toInt())
            timerval = value.toDouble()
            Log.i("Check VAL", highScore[(value.toInt()/30)].toString())
            highscoreLabel.text = highScore[(value.toInt()/30)].toString()
        }

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (switch.isChecked) {
                switchLabel.text = "Show Timer In-Game(OFF)"
                showTimer = false
            } else {
                switchLabel.text = "Show Timer In-Game(ON)"
                showTimer = true
            }
        }


        beginbutton.setOnClickListener{
            //        Check for Cases
//        Case 1, time = 0
            if (timerval == 0.00) {
//            Create snack bar
                val errorsnackBar = Snackbar.make(findViewById(R.id.root_layout),"Please Select Another Time", Snackbar.LENGTH_SHORT)
                errorsnackBar.show()
                return@setOnClickListener
            }


//          PASS THROUGH GATE
//          Create Intent
            val intent = Intent(this, gameActivity::class.java)

            exportTime = secondconvert(slider.value.toInt())
            Log.i("test", exportTime)
            intent.putExtra("timerData", exportTime)
            intent.putExtra("timerSeconds",slider.value.toInt())
            intent.putExtra("showTimer",showTimer)

            position = (slider.value.toInt()/30)
            intent.putExtra("highScore",highScore[position])

            startActivityForResult(intent,REQUEST_CODE)
        }





    }

    fun gameStart(view: View) {

//        Check for Cases
//        Case 1, time = 0
        if (timerval == 0.00) {
//            Create snack bar
            val errorsnackBar = Snackbar.make(findViewById(R.id.root_layout),"Please Select Another Time", Snackbar.LENGTH_SHORT)
            errorsnackBar.show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                highScoreTracker = data.getIntExtra("newHighScore",0)
                highscoreLabel.text = highScoreTracker.toString()
            }
            highScore[position] = highScoreTracker
        }
    }

    fun updateUI () {
        highscoreLabel.text = highscoreSave
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savehighscore", highscoreSave)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        highscoreSave = savedInstanceState.getString("savehighscore", "")
        updateUI()
    }




}
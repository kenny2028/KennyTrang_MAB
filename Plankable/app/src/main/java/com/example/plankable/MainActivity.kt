package com.example.plankable

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


    var timerval: Double = 0.00
    lateinit var layoutRoot : ConstraintLayout
    lateinit var switch : SwitchMaterial
    var switchchecker: Int = 0
    lateinit var switchLabel : TextView
    private val REQUEST_CODE = 1
    lateinit var beginbutton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slider = findViewById<Slider>(R.id.slider)
        val layoutRoot = findViewById<ConstraintLayout>(R.id.root_layout)
        val timerlabel = findViewById<TextView>(R.id.timerlabel)
        val switch = findViewById<SwitchMaterial>(R.id.switch1)
        val switchLabel = findViewById<TextView>(R.id.switch_label)
        val beginbutton = findViewById<Button>(R.id.begin_button)


        slider.addOnChangeListener { slider, value, fromUser ->
            timerlabel.text = secondconvert(value.toInt())
            timerval = value.toDouble()
        }

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (switch.isChecked) {
                switchLabel.text = "Show Timer In-Game(OFF)"
            } else {
                switchLabel.text = "Show Timer In-Game(ON)"
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


//            PASS THROUGH GATE
//            Create Intent
            val intent = Intent(this, gameActivity::class.java)
            Log.i("test", slider.value.toString())
            intent.putExtra("timerData", slider.value.toInt())
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




}
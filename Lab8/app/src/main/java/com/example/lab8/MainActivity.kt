package com.example.lab8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    lateinit var spinner : Spinner
    lateinit var animalLabel : TextView
    lateinit var feedbackLabel : TextView
    lateinit var finalbutton: Button
    lateinit var fab : FloatingActionButton

    var animalSaveString = ""
    var animalFBString = ""


    private var animalData = Animal();
    private var selectedLocationPosition = 0
    private val REQUEST_CODE = 1





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finalbutton = findViewById(R.id.button)
        animalLabel = findViewById(R.id.animalLabel)
        feedbackLabel = findViewById(R.id.feedbackLabel)
        spinner = findViewById(R.id.spinner)
        fab = findViewById(R.id.fab)





        fab.setOnClickListener {
            selectedLocationPosition = spinner.selectedItemPosition
            animalData.setAnimal(selectedLocationPosition)
            Log.i("animal suggested", animalData.name);


            val intent = Intent(this, FeedbackActivity::class.java)
            intent.putExtra("animalType", animalData.name)
            startActivityForResult(intent,REQUEST_CODE)
        }


    }


    fun createPet (view: View) {
        var finaltext = "You really like " + spinner.selectedItem +"s"

        if (spinner.selectedItem == "Fish") {
            finaltext = "You really like " + spinner.selectedItem +"es"
        }

        animalLabel.text = finaltext

        animalSaveString = finaltext




        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("H", "RequestCode:" + requestCode)
        Log.i("H", "ResultCode:" + resultCode )
        if((requestCode == REQUEST_CODE) && (resultCode == RESULT_OK)) {
            feedbackLabel.text = data?.let{data.getStringExtra("message")}
            animalFBString = feedbackLabel.text.toString()
        }
    }

    fun updateUI() {
        animalLabel.text = animalSaveString
        feedbackLabel.text = animalFBString
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("animalString", animalSaveString)
        outState.putString("feedbackString", animalFBString)
        super.onSaveInstanceState(outState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        animalSaveString = savedInstanceState.getString("animalString", "")
        animalFBString = savedInstanceState.getString("feedbackString","")
        updateUI()
    }



}
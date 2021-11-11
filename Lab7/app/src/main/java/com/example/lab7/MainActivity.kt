package com.example.lab7

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

//    Class Variables for images since these are the only things getting updated on life cycle
    lateinit var imageHat: ImageView
    lateinit var imageAnimal: ImageView
    lateinit var imageLight: ImageView
    lateinit var imageShoe: ImageView
    lateinit var imagebackground: ImageView

    var hatId: Int? = null
    var animalId: Int? = null
    var lightId: Int? = null
    var shoeId: Int? = null
    var backgroundId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        get views
        imageAnimal = findViewById<ImageView>(R.id.imageanimal)
        imageHat = findViewById<ImageView>(R.id.imagehat)
        imageLight = findViewById<ImageView>(R.id.imagelight)
        imageShoe = findViewById<ImageView>(R.id.backgroundimage)
        imagebackground = findViewById<ImageView>(R.id.imageshoe)

    }


    fun petButton (view: View) {
        val checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        val  checkBox2 = findViewById<CheckBox>(R.id.checkBox2)


        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val dogButton = findViewById<RadioButton>(R.id.radioButton1)
        val catButton = findViewById<RadioButton>(R.id.radioButton2)

        val layoutRoot = findViewById<ConstraintLayout>(R.id.layout)

        val selectedId = radioGroup.checkedRadioButtonId

        val switch = findViewById<SwitchMaterial>(R.id.switch1)

        val spinner = findViewById<Spinner>(R.id.spinner)

        //Check SelectedId radio button group
        if (selectedId == -1) {
//            Error, no radio button was selected
            val showError = Snackbar.make(layoutRoot, "Please select either dog or cat", Snackbar.LENGTH_SHORT)
            showError.show()
        } else if (selectedId == findViewById<RadioButton>(R.id.radioButton1).id) {
//            imageAnimal.setImageResource(R.drawable.dog)
            animalId = R.drawable.dog
        } else {
            animalId = R.drawable.cat
        }


        //Check for checkboxes
        if (checkBox1.isChecked) {
//            Hat box
            hatId = R.drawable.witchhat
        } else {
            hatId = android.R.color.transparent
        }
        if (checkBox2.isChecked) {
            shoeId = R.drawable.shoes
        } else {
            shoeId = android.R.color.transparent
        }



    //  Check for switch
        if (switch.isChecked) {
            lightId = R.drawable.lights
        } else {
            lightId = android.R.color.transparent
        }

        //Check for Spinner data
        if (spinner.selectedItem == "Winter") {
            backgroundId = R.drawable.winter
        } else if (spinner.selectedItem == "Summer") {
            backgroundId = R.drawable.summer
        }

        updateUI()
    }

    fun updateUI () {
        animalId?.let { imageAnimal.setImageResource(it)}
        hatId?.let { imageHat.setImageResource(it)}
        shoeId?.let { imageShoe.setImageResource(it)}
        lightId?.let { imageLight.setImageResource(it)}
        backgroundId?.let { imagebackground.setImageResource(it)}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        animalId?.let {outState.putInt("image", it)}
        hatId?.let {outState.putInt("hatimage", it)}
        shoeId?.let {outState.putInt("shoeimage", it)}
        lightId?.let {outState.putInt("lightimage", it)}
        backgroundId?.let {outState.putInt("backgroundimage", it)}
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        animalId = savedInstanceState.getInt("image")
        hatId = savedInstanceState.getInt("hatimage")
        shoeId = savedInstanceState.getInt("shoeimage")
        lightId = savedInstanceState.getInt("lightimage")
        backgroundId = savedInstanceState.getInt("backgroundimage")
        updateUI()
    }



}
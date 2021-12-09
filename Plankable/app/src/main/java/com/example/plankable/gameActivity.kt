package com.example.plankable

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.postDelayed
import com.example.plankable.databinding.ActivityGameBinding
import java.util.*


class gameActivity : AppCompatActivity() {


    private lateinit var binding: ActivityGameBinding

    lateinit var bluebox: ImageView
    lateinit var greenbox : ImageView
    lateinit var redbox: ImageView
    lateinit var orangebox : ImageView
    lateinit var pinkbox : ImageView
    lateinit var yellowbox : ImageView

    lateinit var layoutroot: ConstraintLayout

    private var gamePaused: Boolean = true
    lateinit var timerLabel : TextView
    lateinit var roundLabel : TextView
    lateinit var scoreLabel : TextView


//    Grab variables
    private var getTimerData : String? = null
    private var gameSeconds = 0
    private var showTimer = true


//    Game Variables
    private var Score = 0
    private var selectedQ = emptyArray<String>()
    private var gameselectedQ = emptyArray<String>()
    private var colors = arrayOf("red","blue","orange","pink","yellow","green")
    private var round = 1
    private var numofSelections = 0
    private var inSession : Boolean = true

    private var highScore = 0



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

        timerLabel = findViewById(R.id.timerLabel)
        roundLabel = findViewById(R.id.roundLabel)
        scoreLabel = findViewById(R.id.currentScoreLabel)

        roundLabel.text = "Round " + round.toString()

//       GRAB DATA FROM INTENT -----------------------------------------
        //           Check user time selection and set it to appropriate label
        showTimer = intent.getBooleanExtra("showTimer",true)
        if (showTimer) {
//            if timer selected
            getTimerData = intent.getStringExtra("timerData")
            getTimerData?.let { timerLabel.text = getTimerData }
        } else {
            timerLabel.text = ""
        }
        highScore = intent.getIntExtra("highScore",0)
        gameSeconds = intent.getIntExtra("timerSeconds", 0)
        Log.i("totalSECONDs", gameSeconds.toString())
        // ---------------------------------------------------------------
//        Initial Functions Required on Startup

        fun startBeginTimer (seconds: Int){
            object : CountDownTimer(((1000 * seconds).toLong()), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }
                override fun onFinish() {
                    timerLabel.text = "GAME START"
                }
            }.start()
        }

        fun boxshowcolor (boxcolor: String){

            if (boxcolor == "red") {
                redbox.setImageResource(R.drawable.redbox2)
            }
            if (boxcolor == "green") {
                greenbox.setImageResource(R.drawable.greenbox2)
            }
            if (boxcolor == "blue") {
                bluebox.setImageResource(R.drawable.box2)
            }
            if (boxcolor == "pink") {
                pinkbox.setImageResource(R.drawable.pinkbox2)
            }
            if (boxcolor == "orange") {
                orangebox.setImageResource(R.drawable.orangebox2)
            }
            if (boxcolor == "yellow") {
                yellowbox.setImageResource(R.drawable.yellowbox2)
            }
            var mediaPlayer = MediaPlayer.create(this, R.raw.beep)
            mediaPlayer.start()

            object : CountDownTimer(((1000 * 1.5).toLong()), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }
                override fun onFinish() {
                   if (boxcolor == "red") {
                       redbox.setImageResource(R.drawable.redbox1)
                   }else if (boxcolor == "green") {
                       greenbox.setImageResource(R.drawable.greenbox1)
                   }else if (boxcolor == "blue") {
                       bluebox.setImageResource(R.drawable.box1)
                   } else if (boxcolor == "pink") {
                       pinkbox.setImageResource(R.drawable.pinkbox1)
                   } else if (boxcolor == "orange") {
                       orangebox.setImageResource(R.drawable.orangebox1)
                   } else if (boxcolor == "yellow") {
                       yellowbox.setImageResource(R.drawable.yellowbox1)
                   }
                }
            }.start()
        }


        fun boxshowcolor2 (boxcolor: String){

            if (boxcolor == "red") {
                redbox.setImageResource(R.drawable.redbox2)
            }
            if (boxcolor == "green") {
                greenbox.setImageResource(R.drawable.greenbox2)
            }
            if (boxcolor == "blue") {
                bluebox.setImageResource(R.drawable.box2)
            }
            if (boxcolor == "pink") {
                pinkbox.setImageResource(R.drawable.pinkbox2)
            }
            if (boxcolor == "orange") {
                orangebox.setImageResource(R.drawable.orangebox2)
            }
            if (boxcolor == "yellow") {
                yellowbox.setImageResource(R.drawable.yellowbox2)
            }
            var mediaPlayer = MediaPlayer.create(this, R.raw.beep)
            mediaPlayer.start()

            Handler().postDelayed(1500) {
                if (boxcolor == "red") {
                    redbox.setImageResource(R.drawable.redbox1)
                }else if (boxcolor == "green") {
                    greenbox.setImageResource(R.drawable.greenbox1)
                }else if (boxcolor == "blue") {
                    bluebox.setImageResource(R.drawable.box1)
                } else if (boxcolor == "pink") {
                    pinkbox.setImageResource(R.drawable.pinkbox1)
                } else if (boxcolor == "orange") {
                    orangebox.setImageResource(R.drawable.orangebox1)
                } else if (boxcolor == "yellow") {
                    yellowbox.setImageResource(R.drawable.yellowbox1)
                }
            }
        }

        fun runGame() {
            Log.i("RUNGAME", Score.toString())
            Log.i("RUNGAME round", round.toString())


                if (inSession) {
                    if (round == 1) {
                        gameselectedQ += colors.random()
                    } else {
//                        for (i in 1 until (round)) {
//                            Log.i("current i", i.toString())
//                            gameselectedQ += colors.random()
////                        Log.i("Selected Color", gameselectedQ[i])
//
//                        }
                      gameselectedQ += colors.random()
                    }
                }

                for (i in 0 until gameselectedQ.size) {
                    Log.i(" Selected Color", gameselectedQ[i])
                    Log.i("", gameselectedQ[i])
                }






//               show color
//              Delay start of game 2 Seconds


                if (inSession) {
                    if (round == 1) {
                        Handler().postDelayed(2500) {
                            boxshowcolor2(gameselectedQ[0])
                        }
                    } else {
                        for (i in 0 until gameselectedQ.size) {
//                        Log.i("Run handler", i.toString())
                            Log.i("Color Loopin / ", gameselectedQ[i].toString())
                            var num = 2500 * i + 1 + 2500
//                        Log.i("delay second", num.toString())
                            Handler().postDelayed(num.toLong()) {
                                boxshowcolor2(gameselectedQ[i])
//                            Log.i("Color Loopin / ", gameselectedQ[i-1].toString())
                            }

                        }
                        inSession = false

                    }
                }


        }












//            Check when user wants to begin game
        val beginGameAlert = AlertDialog.Builder(this)
        var beginMessage = "Press Start To Begin Game \nTotal Time:(" + secondconvertTEXT(gameSeconds)+ ") \n"+"Highscore: " + highScore.toString()









        beginGameAlert.setMessage(beginMessage)
            .setPositiveButton(R.string.start, DialogInterface.OnClickListener { dialog, which ->
//               START TIMER https://developer.android.com/reference/kotlin/android/os/CountDownTimer
//               -Get Game Seconds
                val countTime: TextView = findViewById(R.id.timerLabel)
                object : CountDownTimer(((1000*gameSeconds).toLong()), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        if (showTimer) {
                            timerLabel.text = secondconvert((millisUntilFinished / 1000).toInt())
                        } else {
                            timerLabel.text = ""
                        }
                    }
                    override fun onFinish() {
                        countTime.text = "Finished"
//                        EXIT
//                        Prepare for entent and score save
                        val endGameAlert = AlertDialog.Builder(this@gameActivity)
                        if (Score>highScore) {
                            highScore = Score
                        }
                        val message = "Game Finished \n"+ "Current Score: " + Score.toString() +"\nHighscore: " + highScore
                        endGameAlert.setMessage(message)
                            .setPositiveButton(R.string.ok) { dialog, which ->
                                //send score then destroy activity
                                val data = Intent()
                                data.putExtra("newHighScore",highScore)
                                setResult(
                                    Activity.RESULT_OK,
                                    data
                                ) //must be set before super.onBackPressed()
                                finish()
                            }
                        endGameAlert.create().show()

                    }
                }.start()
//              START GAME
//              delay 2s before starting (reaction)
//              generate random sequence for round
//              private var Score = 0
//              private var selectedQ = emptyArray<String>()
//              private var gameselectedQ = emptyArray<String>()
//              private var colors = arrayOf("red","blue","orange","pink","yellow","green")
//              private var round = 1

                runGame()


                gamePaused = false
                if (!gamePaused) {
                    bluebox.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN ->
                                {
                                    bluebox.setImageResource(R.drawable.box2) //Do Something
                                    selectedQ += "blue"
                                    for (i in 0 until selectedQ.size) {
                                        System.out.println(selectedQ[i])
                                    }
//
                                    if (numofSelections+1 == round) {
//                                        check for win
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
//                                          you selected right
                                            Score += (1*round)
                                            winRound()
                                            updateScore()
                                            runGame()
                                            System.out.println("ROUND WIN")


                                        } else {
//                                            You selected wrong
//                                            reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()

                                        }
                                    } else {
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
                                            Score += (1*round)
                                            updateScore()
                                            numofSelections++
                                            runGame()

                                        } else {
//                                        you selected wrong reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()
                                        }
                                    }
                                }
                                MotionEvent.ACTION_UP -> bluebox.setImageResource(R.drawable.box1)
                            }

                            return v?.onTouchEvent(event) ?: true
                        }
                    })

                    greenbox.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN ->
                                {
                                    greenbox.setImageResource(R.drawable.greenbox2) //Do Something
                                    selectedQ += "green"
                                    for (i in 0 until selectedQ.size) {
                                        System.out.println(selectedQ[i])
                                    }

                             if (numofSelections+1 == round) {
//                                        check for win
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
//                                          you selected right
                                            Score += (1*round)
                                            winRound()
                                            updateScore()
                                            runGame()
                                            System.out.println("ROUND WIN")


                                        } else {
//                                            You selected wrong
//                                            reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()

                                        }
                                    } else {
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
                                            Score += (1*round)
                                            updateScore()
                                            numofSelections++
                                            runGame()

                                        } else {
//                                        you selected wrong reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()
                                        }
                                    }
                                }
                                MotionEvent.ACTION_UP -> greenbox.setImageResource(R.drawable.greenbox1)
                            }
                            return v?.onTouchEvent(event) ?: true
                        }
                    })

                    redbox.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN ->
                                {
                                    redbox.setImageResource(R.drawable.redbox2) //Do Something
                                    selectedQ += "red"
                                    for (i in 0 until selectedQ.size) {
                                        System.out.println(selectedQ[i])
                                    }

//                                    Check if right selection
                                    if (numofSelections+1 == round) {
//                                        check for win
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
//                                          you selected right
                                            Score += (1*round)
                                            winRound()
                                            updateScore()
                                            runGame()
                                            System.out.println("ROUND WIN")


                                        } else {
//                                            You selected wrong
//                                            reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()

                                        }
                                    } else {
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
                                            Score += (1*round)
                                            updateScore()
                                            numofSelections++
                                            runGame()

                                        } else {
//                                        you selected wrong reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()
                                        }
                                    }
                                }
                                MotionEvent.ACTION_UP -> redbox.setImageResource(R.drawable.redbox1)
                            }
                            return v?.onTouchEvent(event) ?: true
                        }
                    })

                    orangebox.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN ->
                                {
                                    orangebox.setImageResource(R.drawable.orangebox2) //Do Something
                                    selectedQ += "orange"
                                    for (i in 0 until selectedQ.size) {
                                        System.out.println(selectedQ[i])
                                    }

//                                    Check if right selection
                                    if (numofSelections+1 == round) {
//                                        check for win
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
//                                          you selected right
                                            Score += (1*round)
                                            winRound()
                                            updateScore()
                                            runGame()
                                            System.out.println("ROUND WIN")


                                        } else {
//                                            You selected wrong
//                                            reset game
                                            loseRound()
                                            runGame()

                                        }
                                    } else {
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
                                            Score += (1*round)
                                            updateScore()
                                            numofSelections++
                                            runGame()

                                        } else {
//                                        you selected wrong reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()
                                        }
                                    }
                                }
                                MotionEvent.ACTION_UP -> orangebox.setImageResource(R.drawable.orangebox1)
                            }
                            return v?.onTouchEvent(event) ?: true
                        }
                    })

                    yellowbox.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN ->
                                {
                                    yellowbox.setImageResource(R.drawable.yellowbox2) //Do Something
                                    selectedQ += "yellow"
                                    for (i in 0 until selectedQ.size) {
                                        System.out.println(selectedQ[i])
                                    }

//                                    Check if right selection
                                    if (numofSelections+1 == round) {
//                                        check for win
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
//                                          you selected right
                                            Score += (1*round)
                                            winRound()
                                            updateScore()
                                            runGame()
                                            System.out.println("ROUND WIN")


                                        } else {
//                                            You selected wrong
//                                            reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()

                                        }
                                    } else {
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
                                            Score += (1*round)
                                            updateScore()
                                            numofSelections++
                                            runGame()

                                        } else {
//                                        you selected wrong reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()
                                        }
                                    }
                                }
                                MotionEvent.ACTION_UP -> yellowbox.setImageResource(R.drawable.yellowbox1)
                            }
                            return v?.onTouchEvent(event) ?: true
                        }
                    })

                    pinkbox.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN ->
                                {
                                    pinkbox.setImageResource(R.drawable.pinkbox2) //Do Something
                                    selectedQ += "pink"
                                    for (i in 0 until selectedQ.size) {
                                        System.out.println(selectedQ[i])
                                    }

//                                    Check if right selection
                                    if (numofSelections+1 == round) {
//                                        check for win
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
//                                          you selected right
                                            Score += (1*round)
                                            winRound()
                                            updateScore()
                                            runGame()
                                            System.out.println("ROUND WIN")


                                        } else {
                                            Log.i("Loss point", Score.toString())
//                                            You selected wrong
//                                            reset game
                                            loseRound()
                                            runGame()

                                        }
                                    } else {
                                        if (selectedQ[numofSelections] == gameselectedQ[numofSelections]) {
                                            Score += (1*round)
                                            updateScore()
                                            numofSelections++
                                            runGame()

                                        } else {
//                                        you selected wrong reset game
                                            Log.i("Loss point", Score.toString())
                                            loseRound()
                                            runGame()
                                        }
                                    }
                                }
                                MotionEvent.ACTION_UP -> pinkbox.setImageResource(R.drawable.pinkbox1)
                            }
                            return v?.onTouchEvent(event) ?: true
                        }
                    })
                }





                return@OnClickListener
            })

//        Show Alert
        beginGameAlert.create().show()

    }// ON CREATE END

    override fun onBackPressed() {
        val data = Intent()
        setResult(Activity.RESULT_OK, data) //must be set before super.onBackPressed()
        super.onBackPressed()
        finish()
    }








//    FUNCTIONS ------------------------------------
fun secondconvert(seconds: Int):String {
    var minutes = seconds/60
//        minutes = floor(minutes)
    var finalString = ""

    var newseconds = seconds % 60

    minutes = minutes.toInt()

    if(newseconds == 0) {
        var secondszero = "00"
        finalString = minutes.toString() + ":" + secondszero
    }else if (newseconds <= 9 ) {
        finalString = minutes.toString() + ":0" + newseconds.toString()
    } else {
        finalString = minutes.toString() + ":" + newseconds.toString()
    }


//            return new string format

    return finalString
}

//    Used for SetMessage in Dialog
fun secondconvertTEXT(seconds: Int):String {
    var minutes = seconds/60
//        minutes = floor(minutes)
    var finalString = ""

    var newseconds = seconds % 60

    minutes = minutes.toInt()

   if(newseconds == 0) {
        var secondszero = "00"
        if (minutes == 1) {
            finalString = minutes.toString() + " minute"
            return finalString
        }
        finalString = minutes.toString() + " minutes"
    }else if (newseconds <= 9 ) {
        if (minutes.toString() == "1") {
            finalString = minutes.toString() + " minute " + newseconds.toString() + " seconds"
            return finalString
        }
        finalString = minutes.toString() + " minutes " + newseconds.toString() + " seconds"
    } else {
        if (minutes.toString() == "1") {
            finalString = minutes.toString() + " minute " + newseconds.toString() + " seconds"
            return finalString
        } else if (minutes.toString() == "0") {
            finalString = newseconds.toString() + " seconds"
            return finalString
        }
        finalString = minutes.toString() + " minutes " + newseconds.toString() + " seconds"
    }


//            return new string format

    return finalString
}

fun buttonsoundpress(view: View) {
    var mediaPlayer1 = MediaPlayer.create(this, R.raw.beep)
    mediaPlayer1.start()
}

fun updateScore() {
    scoreLabel.text = Score.toString()
}

fun winRound() {
    round++
    numofSelections = 0
    selectedQ = emptyArray()
    inSession = true
    roundLabel.text = "Round " + round.toString()
}

fun loseRound() {
    round = 1
    numofSelections = 0
    selectedQ = emptyArray()
    gameselectedQ = emptyArray()
    inSession = true
    roundLabel.text = "Round " + round.toString()
}







}//APP COMPACT DON'T TOUCH




















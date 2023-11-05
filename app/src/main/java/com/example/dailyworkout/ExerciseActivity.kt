package com.example.dailyworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.dailyworkout.databinding.ActivityExerciseBinding
class ExerciseActivity : AppCompatActivity() {
    private var exerciseBinding:ActivityExerciseBinding? = null
    private var countDownTimer: CountDownTimer? = null
    private var timerDuration: Long = 10000
    private var pauseOffset: Long = 0
    private val countInterval: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseBinding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseBinding?.root)
        //setContentView(R.layout.activity_exercise)
        setSupportActionBar(exerciseBinding?.exerciseTB)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseBinding?.exerciseTB?.setNavigationOnClickListener {
            onBackPressed()
        }

        resetProgressBar()

    }

    override fun onDestroy() {
        super.onDestroy()
        exerciseBinding = null
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            pauseOffset = 0
        }
    }

    private fun setProgressBar() {
        countDownTimer = object: CountDownTimer(timerDuration, countInterval) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                exerciseBinding?.exersisePB?.progress = (millisUntilFinished/1000+1).toInt()
                exerciseBinding?.timerTV?.text = (millisUntilFinished/1000+1).toString()
            }

            override fun onFinish() {
                pauseOffset += countInterval
                exerciseBinding?.exersisePB?.progress = (10-pauseOffset/1000).toInt()
                exerciseBinding?.timerTV?.text = (10-pauseOffset/1000).toString()
                Toast.makeText(this@ExerciseActivity,
                    "Now exercise will start",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }

    private fun resetProgressBar() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            pauseOffset = 0
        }
        setProgressBar()
    }
}
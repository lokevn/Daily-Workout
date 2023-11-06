package com.example.dailyworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.dailyworkout.databinding.ActivityExerciseBinding
class ExerciseActivity : AppCompatActivity() {
    private var exerciseBinding:ActivityExerciseBinding? = null
    private var countDownTimer: CountDownTimer? = null
    private var timerDuration: Long = 10000
    private var pauseOffset: Long = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseDuration: Long = 10000
    private var pauseExerciseOffset: Long = 0
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

        setupProgressBar()

    }

    override fun onDestroy() {
        super.onDestroy()
        exerciseBinding = null
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
        }
    }

    private fun setProgressBar() {
        timerDuration = 10000
        exerciseBinding?.exersisePB?.max = (timerDuration/1000).toInt()
        pauseOffset = 0
        countDownTimer = object: CountDownTimer(timerDuration, countInterval) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                exerciseBinding?.exersisePB?.progress = (millisUntilFinished/1000+1).toInt()
                exerciseBinding?.timerTV?.text = (millisUntilFinished/1000+1).toString()
            }

            override fun onFinish() {
                pauseOffset += countInterval
                exerciseBinding?.exersisePB?.progress = ((timerDuration-pauseOffset)/1000).toInt()
                exerciseBinding?.timerTV?.text = ((timerDuration-pauseOffset)/1000).toString()
                setupExerciseView()
            }
        }.start()
    }

    private fun setupProgressBar() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }

        exerciseBinding?.exerciseTV?.text = "READY FOR"
        exerciseBinding?.exerciseFL?.visibility = View.VISIBLE
        exerciseBinding?.exerciseViewFL?.visibility = View.INVISIBLE
        setProgressBar()
    }

    private fun setExerciseView() {
        exerciseDuration = 30000
        exerciseBinding?.exersiseViewPB?.max = (exerciseDuration/1000).toInt()
        pauseExerciseOffset = 0
        exerciseTimer = object: CountDownTimer(exerciseDuration, countInterval) {
            override fun onTick(millisUntilFinished: Long) {
                pauseExerciseOffset = exerciseDuration - millisUntilFinished
                exerciseBinding?.exersiseViewPB?.progress = (millisUntilFinished/1000+1).toInt()
                exerciseBinding?.timerViewTV?.text = (millisUntilFinished/1000+1).toString()
            }

            override fun onFinish() {
                pauseExerciseOffset += countInterval
                exerciseBinding?.exersiseViewPB?.progress = ((exerciseDuration-pauseExerciseOffset)/1000).toInt()
                exerciseBinding?.timerViewTV?.text = ((exerciseDuration-pauseExerciseOffset)/1000).toString()
                Toast.makeText(this@ExerciseActivity,
                    "exercise time out",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }

    private fun setupExerciseView() {
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
        }
        exerciseBinding?.exerciseTV?.text = "NOW START"
        exerciseBinding?.exerciseFL?.visibility = View.INVISIBLE
        exerciseBinding?.exerciseViewFL?.visibility = View.VISIBLE
        setExerciseView()
    }

    private fun startExercise() {
        timerDuration = 30000
        exerciseBinding?.exersisePB?.max = (timerDuration/1000).toInt()
        pauseOffset = 0
        countDownTimer = object: CountDownTimer(timerDuration, countInterval) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                exerciseBinding?.exersisePB?.progress = (millisUntilFinished/1000+1).toInt()
                exerciseBinding?.timerTV?.text = (millisUntilFinished/1000+1).toString()
            }

            override fun onFinish() {
                pauseOffset += countInterval
                exerciseBinding?.exersisePB?.progress = ((timerDuration-pauseOffset)/1000).toInt()
                exerciseBinding?.timerTV?.text = ((timerDuration-pauseOffset)/1000).toString()
                Toast.makeText(this@ExerciseActivity,
                    "Exercise time out",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }
}
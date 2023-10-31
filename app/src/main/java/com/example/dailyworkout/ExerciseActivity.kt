package com.example.dailyworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailyworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var exerciseBinding:ActivityExerciseBinding? = null
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


    }

    override fun onDestroy() {
        super.onDestroy()
        exerciseBinding = null
    }
}
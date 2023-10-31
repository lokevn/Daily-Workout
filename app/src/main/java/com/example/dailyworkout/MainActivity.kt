package com.example.dailyworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailyworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mainBinding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(mainBinding?.root)

        //val btnStart = findViewById<FrameLayout>(R.id.StartFL)
        mainBinding?.StartFL?.setOnClickListener {
            val intent = Intent(this,
                ExerciseActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
}
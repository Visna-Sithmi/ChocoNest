package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OnboardScreen2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard_screen2)

        val btnNext = findViewById<Button>(R.id.button3)
        btnNext.setOnClickListener {
            startActivity(Intent(this, OnboardScreen2Activity::class.java))
        }
    }
}

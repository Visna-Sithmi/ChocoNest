package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class OnboardScreen1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.onboard_screen1)

        // 1) Draw your background behind status & nav bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 2) (Optional) Pad your root so text/buttons aren’t cut off by bars
        val root: View = findViewById(R.id.favourites)   // root id in your XML
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Navigation
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, OnboardScreen2Activity::class.java))
        }
    }
}

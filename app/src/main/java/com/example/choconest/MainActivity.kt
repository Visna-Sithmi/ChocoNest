package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        // Attach insets to the ROOT layout (id must exist in activity_welcome.xml)
        val root: View = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val sysBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sysBars.left, sysBars.top, sysBars.right, sysBars.bottom)
            insets
        }

        // Use the actual id of your "Get Started" button
        val btnGetStarted = findViewById<Button>(R.id.button) // <-- change to your real id
        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, OnboardScreen1Activity::class.java))
        }
    }
}

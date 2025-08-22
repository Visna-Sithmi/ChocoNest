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

class OnboardScreen2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.onboard_screen2)

        // Draw behind status & nav bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Pad the root so content isn't covered by system bars
        val root: View = findViewById(R.id.onboard2)   // make sure your root in onboard_screen2.xml has android:id="@+id/main"
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Navigate to Login
        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

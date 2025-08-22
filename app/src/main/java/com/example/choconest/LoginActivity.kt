package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val btnLogin = findViewById<Button>(R.id.btnLogin)   // your login button
        val btnSignUp = findViewById<TextView>(R.id.btnSignUp) // "Don't have an account? Sign Up"

        // Navigate to SignupActivity
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}

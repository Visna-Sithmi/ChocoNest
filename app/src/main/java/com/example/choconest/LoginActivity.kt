package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignUp = findViewById<TextView>(R.id.btnSignUp)

        // ✅ Login Button Click
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            when {
                username.isEmpty() -> {
                    etUsername.error = "Username is required"
                }
                password.isEmpty() -> {
                    etPassword.error = "Password is required"
                }
                !isValidPassword(password) -> {
                    etPassword.error = "Password must contain 1 uppercase & 1 special character"
                }
                else -> {
                    // ✅ Passed all checks → Go to Profile Page
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
            }
        }

        // ✅ Navigate to SignupActivity
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    // Password must have 1 uppercase + 1 special character + min 6 characters
    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{6,}\$")
        return passwordPattern.matches(password)
    }
}

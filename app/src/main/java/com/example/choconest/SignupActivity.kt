package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)

        // Apply insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.favourites)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnGoLogin = findViewById<TextView>(R.id.btnGoLogin)
        val btnCreateAccount = findViewById<TextView>(R.id.btnCreateAccount)

        // Navigate back to Login
        btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // prevents going back to signup
        }

        // ✅ Create Account Button
        btnCreateAccount.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            when {
                fullName.isEmpty() -> {
                    etFullName.error = "Full name is required"
                }
                email.isEmpty() -> {
                    etEmail.error = "Email is required"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = "Enter a valid email"
                }
                password.isEmpty() -> {
                    etPassword.error = "Password is required"
                }
                !isValidPassword(password) -> {
                    etPassword.error = "Password must contain 1 uppercase & 1 special character"
                }
                confirmPassword.isEmpty() -> {
                    etConfirmPassword.error = "Please confirm password"
                }
                password != confirmPassword -> {
                    etConfirmPassword.error = "Passwords do not match"
                }
                else -> {
                    // ✅ All validations passed
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                }
            }
        }
    }

    // Password must have 1 uppercase + 1 special character + min 6 characters
    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{6,}\$")
        return passwordPattern.matches(password)
    }
}

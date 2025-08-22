package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home)

        findViewById<Button>(R.id.btn_about_us).setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navFavorite = findViewById<ImageView>(R.id.nav_favorite)
        val navCart = findViewById<ImageView>(R.id.nav_cart)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Highlight Home in dark chocolate
        navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_chocolate))

        // Keep others in cream
        navFavorite.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.cream))


    }
}
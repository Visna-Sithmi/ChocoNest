package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navFavorite = findViewById<ImageView>(R.id.nav_favorite)
        val navCart = findViewById<ImageView>(R.id.nav_cart)

        // Highlight current (Home)
        navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_chocolate))
        navFavorite.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.cream))

        // Navigation
        navFavorite.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }
        navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        findViewById<Button>(R.id.btn_about_us).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        findViewById<Button>(R.id.btn_customize_gifts).setOnClickListener {
            startActivity(Intent(this, GiftsActivity::class.java))
        }
    }
}

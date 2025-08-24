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

class ContactusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contact_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navFavorite = findViewById<ImageView>(R.id.nav_favorite)
        val navCart = findViewById<ImageView>(R.id.nav_cart)


        navFavorite.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navHome.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.cream))

        // Navigation
        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        navFavorite.setOnClickListener {
            startActivity(Intent(this, FavouritesActivity::class.java))
        }

        findViewById<Button>(R.id.btnBackHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
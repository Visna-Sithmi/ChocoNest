package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CartActivity : AppCompatActivity() {

    private lateinit var itemQuantity: TextView
    private lateinit var totalPrice: TextView

    private var quantity = 1
    private val itemPrice = 360   // Rs. 360 per item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cart)

        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }

        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navFavorite = findViewById<ImageView>(R.id.nav_favorite)
        val navCart = findViewById<ImageView>(R.id.nav_cart)


        navFavorite.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navHome.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_chocolate))

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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mycart_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find views
        itemQuantity = findViewById(R.id.itemQuantity)
        totalPrice = findViewById(R.id.totalPrice)
        val btnIncrease = findViewById<ImageButton>(R.id.btnIncrease)
        val btnDecrease = findViewById<ImageButton>(R.id.btnDecrease)

        // Initialize total
        updateTotal()

        // Increase quantity
        btnIncrease.setOnClickListener {
            quantity++
            updateViews()
        }

        // Decrease quantity
        btnDecrease.setOnClickListener {
            if (quantity > 1) { // prevent going below 1
                quantity--
                updateViews()
            }
        }
    }

    private fun updateViews() {
        itemQuantity.text = quantity.toString()
        updateTotal()
    }

    private fun updateTotal() {
        val total = quantity * itemPrice
        totalPrice.text = "Total: Rs. $total/-"
    }
}

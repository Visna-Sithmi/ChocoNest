package com.example.choconest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PackagingsActivity : AppCompatActivity() {
    private var isFirstSelection = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.packagings)

        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navFavorite = findViewById<ImageView>(R.id.nav_favorite)
        val navCart = findViewById<ImageView>(R.id.nav_cart)

        // Highlight Profile footer (for example: Favorite active here)
        navFavorite.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navHome.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.cream))

        // Handle clicks
        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        navFavorite.setOnClickListener {
            startActivity(Intent(this, FavouritesActivity::class.java))
        }
        navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }


        // Apply insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.packaging_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ================================
        // Spinner setup
        // ================================
        val spinner: Spinner = findViewById(R.id.categorySpinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.cart_categories,
            R.layout.spinner_item
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = adapter

        // Pre-select Packaging Materials
        val position = adapter.getPosition("Packaging Materials")
        spinner.setSelection(position)

        // Handle selection
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isFirstSelection) {
                    isFirstSelection = false
                    return
                }

                val selectedItem = parent.getItemAtPosition(position).toString()

                when (selectedItem) {
                    "Chocolates" -> {
                        startActivity(Intent(this@PackagingsActivity, GiftsActivity::class.java))
                        finish()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}

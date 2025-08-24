package com.example.choconest

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.InputStream

class PaymentActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var slipPreview: ImageView
    private lateinit var btnSubmitPayment: Button
    private var isSlipUploaded = false
    private var slipUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.payment)

        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navFavorite = findViewById<ImageView>(R.id.nav_favorite)
        val navCart = findViewById<ImageView>(R.id.nav_cart)

        // Footer active colors
        navFavorite.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navHome.setColorFilter(ContextCompat.getColor(this, R.color.cream))
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.cream))

        // Navigation
        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        navFavorite.setOnClickListener {
            startActivity(Intent(this, FavouritesActivity::class.java))
        }
        navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.payment_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        etPhone = findViewById(R.id.etPhone)
        slipPreview = findViewById(R.id.slipPreview)
        val btnUploadSlip = findViewById<Button>(R.id.btnUploadSlip)
        btnSubmitPayment = findViewById(R.id.btnSubmitPayment)

        // Disable submit initially
        btnSubmitPayment.isEnabled = false

        // Upload slip → open gallery
        btnUploadSlip.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(intent)
        }

        // Validate & Submit
        btnSubmitPayment.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Payment Submitted Successfully!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, ThankYouPaymentActivity::class.java))
                finish()
            }
        }
    }

    // Handle Gallery Result
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            slipUri = result.data!!.data
            try {
                val inputStream: InputStream? = contentResolver.openInputStream(slipUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                slipPreview.setImageBitmap(bitmap)
                isSlipUploaded = true
                Toast.makeText(this, "Slip Uploaded Successfully", Toast.LENGTH_SHORT).show()

                // Enable submit button if all fields are filled
                checkIfFormComplete()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Failed to load slip", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Check if all fields are filled → enable Submit button
    private fun checkIfFormComplete() {
        val name = etName.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val phone = etPhone.text.toString().trim()

        btnSubmitPayment.isEnabled = name.isNotEmpty() &&
                address.isNotEmpty() &&
                phone.isNotEmpty() &&
                isSlipUploaded
    }

    // Validation Function
    private fun validateForm(): Boolean {
        val name = etName.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val phone = etPhone.text.toString().trim()

        return when {
            name.isEmpty() -> {
                etName.error = "Full name is required"
                false
            }
            !name.matches(Regex("^[A-Za-z ]+\$")) -> {
                etName.error = "Name must contain only letters"
                false
            }
            address.isEmpty() -> {
                etAddress.error = "Address is required"
                false
            }
            address.length < 5 -> {
                etAddress.error = "Enter a valid address"
                false
            }
            phone.isEmpty() -> {
                etPhone.error = "Phone number is required"
                false
            }
            !phone.matches(Regex("^\\d{10}\$")) -> {
                etPhone.error = "Enter a valid 10-digit phone number"
                false
            }
            !isSlipUploaded -> {
                Toast.makeText(this, "Please upload your payment slip", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}

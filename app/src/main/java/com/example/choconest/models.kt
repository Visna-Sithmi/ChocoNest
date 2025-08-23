package com.example.choconest

// Product data class
data class Product(val name: String, val imageRes: Int)

// Category data class with list of products
data class Category(val name: String, val products: List<Product>)

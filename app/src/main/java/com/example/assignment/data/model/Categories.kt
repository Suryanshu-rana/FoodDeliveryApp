package com.example.assignment.data.model


data class Categories(
    val status: Boolean,
    var message: String,
    var error: Any,
    var categories: List<Product>
)
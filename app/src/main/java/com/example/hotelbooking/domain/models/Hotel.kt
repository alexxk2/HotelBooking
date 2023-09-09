package com.example.hotelbooking.domain.models

data class Hotel(
    val name: String,
    val address: String,
    val price: Int,
    val priceDescription: String,
    val rating: Int,
    val ratingName: String,
    val imageUrls: List<String>,
    val description: String,
    val peculiarities: List<String>
)
package com.example.hotelbooking.domain.models

data class Room(
    val name: String,
    val price: String,
    val priceDescription: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>
)

package com.example.hotelbooking.data.network.dto

import com.google.gson.annotations.SerializedName

data class RoomDto(
    @SerializedName("image_urls") val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    @SerializedName("price_per") val priceDescription: String
)
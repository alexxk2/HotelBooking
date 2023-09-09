package com.example.hotelbooking.data

import com.example.hotelbooking.data.network.dto.AboutTheHotel
import com.google.gson.annotations.SerializedName

data class HotelDto(
    @SerializedName("about_the_hotel") val aboutTheHotel: AboutTheHotel,
    @SerializedName("adress") val address: String,
    @SerializedName("image_urls") val imageUrls: List<String>,
    @SerializedName("minimal_price") val price: Int,
    val name: String,
    @SerializedName("price_for_it") val priceDescription: String,
    val rating: Int,
    @SerializedName("rating_name") val ratingName: String
)
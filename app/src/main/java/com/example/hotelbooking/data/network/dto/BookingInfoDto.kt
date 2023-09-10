package com.example.hotelbooking.data.network.dto

import com.google.gson.annotations.SerializedName

data class BookingInfoDto(
    @SerializedName("arrival_country") val arrivalCountry: String,
    val departure: String,
    @SerializedName("fuel_charge") val fuelCharge: Int,
    @SerializedName("horating") val rating: Int,
    @SerializedName("hotel_adress") val hotelAddress: String,
    @SerializedName("hotel_name") val hotelName: String,
    @SerializedName("number_of_nights") val numberOfNights: Int,
    val nutrition: String,
    @SerializedName("rating_name") val ratingName: String,
    @SerializedName("room") val roomType: String,
    @SerializedName("service_charge") val serviceCharge: Int,
    @SerializedName("tour_date_start") val tourDateStart: String,
    @SerializedName("tour_date_stop") val tourDateEnd: String,
    @SerializedName("tour_price") val tourPrice: Int
)
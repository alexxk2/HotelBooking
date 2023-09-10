package com.example.hotelbooking.domain.models

data class BookingInfo(
    val hotelName: String,
    val hotelAddress: String,
    val rating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateEnd: String,
    val numberOfNights: Int,
    val roomType: String,
    val nutrition: String,
    val tourPrice: String,
    val fuelCharge: String,
    val serviceCharge: String,
    val totalPrice: String
)


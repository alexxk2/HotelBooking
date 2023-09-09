package com.example.hotelbooking.data.network

import com.example.hotelbooking.data.HotelDto

interface NetworkClient {

    suspend fun getHotelInfo(): List<HotelDto>
}
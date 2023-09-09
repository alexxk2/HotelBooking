package com.example.hotelbooking.domain.repositories

import com.example.hotelbooking.domain.models.Hotel

interface HotelRepository {

    suspend fun getHotelInfo(): List<Hotel>
}
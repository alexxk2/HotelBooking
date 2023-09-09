package com.example.hotelbooking.domain.repositories

import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.domain.models.Room

interface HotelRepository {

    suspend fun getHotelInfo(): List<Hotel>
    suspend fun getRoomsInfo(): List<Room>
}
package com.example.hotelbooking.data.network

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.dto.RoomDto

interface NetworkClient {

    suspend fun getHotelInfo(): List<HotelDto>
    suspend fun getRoomsInfo(): List<RoomDto>
}
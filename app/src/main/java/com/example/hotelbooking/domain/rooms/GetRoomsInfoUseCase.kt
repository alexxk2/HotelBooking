package com.example.hotelbooking.domain.rooms

import com.example.hotelbooking.domain.models.Room
import com.example.hotelbooking.domain.repositories.HotelRepository

class GetRoomsInfoUseCase(private val hotelRepository: HotelRepository) {

    suspend fun execute(): List<Room> = hotelRepository.getRoomsInfo()
}
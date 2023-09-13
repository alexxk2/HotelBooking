package com.example.hotelbooking.domain.hotel

import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.domain.repositories.HotelRepository

class GetHotelInfoUseCase(private val hotelRepository: HotelRepository) {

    suspend fun execute(): List<Hotel> = hotelRepository.getHotelInfo()
}
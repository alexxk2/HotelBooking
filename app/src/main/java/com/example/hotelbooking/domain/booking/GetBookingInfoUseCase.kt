package com.example.hotelbooking.domain.booking

import com.example.hotelbooking.domain.models.BookingInfo
import com.example.hotelbooking.domain.repositories.HotelRepository

class GetBookingInfoUseCase(private val hotelRepository: HotelRepository) {

    suspend fun execute(): List<BookingInfo> = hotelRepository.getBookingInfo()
}
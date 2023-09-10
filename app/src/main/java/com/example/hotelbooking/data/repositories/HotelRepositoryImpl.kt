package com.example.hotelbooking.data.repositories

import com.example.hotelbooking.data.converters.NetworkConverter
import com.example.hotelbooking.data.network.NetworkClient
import com.example.hotelbooking.domain.models.BookingInfo
import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.domain.models.Room
import com.example.hotelbooking.domain.repositories.HotelRepository

class HotelRepositoryImpl(
    private val networkClient: NetworkClient,
    private val networkConverter: NetworkConverter
) : HotelRepository {

    override suspend fun getHotelInfo(): List<Hotel> {
        val resultFromData = networkClient.getHotelInfo()
        return resultFromData.map { hotelDto ->
            networkConverter.convertHotelToDomain(hotelDto)
        }
    }

    override suspend fun getRoomsInfo(): List<Room> {
        val resultFromData = networkClient.getRoomsInfo()
        return resultFromData.map { roomDto ->
            networkConverter.convertRoomToDomain(roomDto)
        }
    }

    override suspend fun getBookingInfo(): List<BookingInfo> {
        val resultFromData = networkClient.getBookingInfo()
        return resultFromData.map { bookingInfoDto ->
            networkConverter.convertBookingInfoToDomain(bookingInfoDto)
        }
    }
}
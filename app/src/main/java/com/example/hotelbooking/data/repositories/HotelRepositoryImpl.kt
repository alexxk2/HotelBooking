package com.example.hotelbooking.data.repositories

import com.example.hotelbooking.data.converters.NetworkConverter
import com.example.hotelbooking.data.network.NetworkClient
import com.example.hotelbooking.domain.models.Hotel
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
}
package com.example.hotelbooking.data.converters

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.dto.RoomDto
import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.domain.models.Room

class NetworkConverter {

    fun convertHotelToDomain(hotelDto: HotelDto): Hotel {
        with(hotelDto) {
            return Hotel(
                name = name,
                address = address,
                price = price,
                priceDescription = priceDescription,
                rating = rating,
                ratingName = ratingName,
                imageUrls = imageUrls,
                description = aboutTheHotel.description,
                peculiarities = aboutTheHotel.peculiarities
            )
        }
    }

    fun convertRoomToDomain(roomDto: RoomDto): Room {
        with(roomDto) {
            return Room(
                name = name,
                price = price,
                priceDescription = priceDescription,
                peculiarities = peculiarities,
                imageUrls = imageUrls
            )
        }
    }
}
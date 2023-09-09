package com.example.hotelbooking.data.converters

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.domain.models.Hotel

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
}
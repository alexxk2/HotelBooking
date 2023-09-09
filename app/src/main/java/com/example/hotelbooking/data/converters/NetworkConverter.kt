package com.example.hotelbooking.data.converters

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.dto.RoomDto
import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.domain.models.Room
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class NetworkConverter {

    fun convertHotelToDomain(hotelDto: HotelDto): Hotel {
        with(hotelDto) {
            return Hotel(
                name = name,
                address = address,
                price = addSpacesToBigNumber(price),
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
                price = addSpacesToBigNumber(price),
                priceDescription = priceDescription,
                peculiarities = peculiarities,
                imageUrls = imageUrls
            )
        }
    }

    private fun addSpacesToBigNumber(number: Int): String {
        val decimalFormat = DecimalFormat()
        decimalFormat.isGroupingUsed = true
        decimalFormat.groupingSize = 3
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = ' '
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols

        return decimalFormat.format(number)
    }
}
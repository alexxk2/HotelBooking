package com.example.hotelbooking.data.converters

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.dto.BookingInfoDto
import com.example.hotelbooking.data.network.dto.RoomDto
import com.example.hotelbooking.domain.models.BookingInfo
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

    fun convertBookingInfoToDomain(bookingInfoDto: BookingInfoDto): BookingInfo {
        with(bookingInfoDto) {
            return BookingInfo(
                hotelName = hotelName,
                hotelAddress = hotelAddress,
                rating = rating,
                ratingName = ratingName,
                departure = departure,
                arrivalCountry = arrivalCountry,
                tourDateStart = tourDateStart,
                tourDateEnd = tourDateEnd,
                numberOfNights = numberOfNights,
                roomType = roomType,
                nutrition = nutrition,
                tourPrice = addSpacesToBigNumber(tourPrice),
                fuelCharge = addSpacesToBigNumber(fuelCharge),
                serviceCharge = addSpacesToBigNumber(serviceCharge),
                totalPrice = addSpacesToBigNumber(tourPrice + fuelCharge + serviceCharge)
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
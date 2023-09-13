package com.example.hotelbooking.di

import com.example.hotelbooking.domain.booking.GetBookingInfoUseCase
import com.example.hotelbooking.domain.hotel.GetHotelInfoUseCase
import com.example.hotelbooking.domain.rooms.GetRoomsInfoUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHotelInfoUseCase> { GetHotelInfoUseCase(hotelRepository = get()) }
    factory<GetRoomsInfoUseCase> { GetRoomsInfoUseCase(hotelRepository = get()) }
    factory<GetBookingInfoUseCase> { GetBookingInfoUseCase(hotelRepository = get()) }
}
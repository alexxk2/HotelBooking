package com.example.hotelbooking.di

import com.example.hotelbooking.domain.hotel.GetHotelInfoUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHotelInfoUseCase> {GetHotelInfoUseCase(hotelRepository = get())  }
}
package com.example.hotelbooking.di

import com.example.hotelbooking.presentation.hotel.view_model.HotelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<HotelViewModel> { HotelViewModel(getHotelInfoUseCase = get()) }
}
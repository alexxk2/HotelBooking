package com.example.hotelbooking.di

import com.example.hotelbooking.presentation.booking.view_model.BookingViewModel
import com.example.hotelbooking.presentation.hotel.view_model.HotelViewModel
import com.example.hotelbooking.presentation.order.view_model.OrderViewModel
import com.example.hotelbooking.presentation.rooms.view_model.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<HotelViewModel> { HotelViewModel(getHotelInfoUseCase = get()) }
    viewModel<RoomsViewModel> { RoomsViewModel(getRoomsInfoUseCase = get()) }
    viewModel<BookingViewModel> { BookingViewModel(getBookingInfoUseCase = get()) }
    viewModel<OrderViewModel> { OrderViewModel() }
}
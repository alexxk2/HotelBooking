package com.example.hotelbooking.data.network

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.dto.BookingInfoDto
import com.example.hotelbooking.data.network.dto.RoomsResponseEntity
import retrofit2.Response
import retrofit2.http.GET


interface HotelsApiService {

    @GET("/v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelInfo(): Response<HotelDto>

    @GET("/v3/f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRoomsInfo(): Response<RoomsResponseEntity>

    @GET("/v3/e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBookingInfo(): Response<BookingInfoDto>

}
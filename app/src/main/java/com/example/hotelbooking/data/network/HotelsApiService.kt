package com.example.hotelbooking.data.network

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.dto.BookingInfoDto
import com.example.hotelbooking.data.network.dto.RoomsResponseEntity
import retrofit2.Response
import retrofit2.http.GET


interface HotelsApiService {

    @GET("/v1/90b81dc4-5b63-4451-8d31-bdd4e678c849")
    suspend fun getHotelInfo(): Response<HotelDto>

    @GET("/v1/ed7367d1-9b5b-4123-be5b-778819aa3c3b")
    suspend fun getRoomsInfo(): Response<RoomsResponseEntity>

    @GET("/v1/823681dd-ccbb-41c4-8f13-0ec5bf4e1706")
    suspend fun getBookingInfo(): Response<BookingInfoDto>

}
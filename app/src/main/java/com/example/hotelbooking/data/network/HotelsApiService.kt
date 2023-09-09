package com.example.hotelbooking.data.network

import com.example.hotelbooking.data.HotelDto
import retrofit2.Response
import retrofit2.http.GET


interface HotelsApiService {

    @GET("/v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelInfo(): Response<HotelDto>

}
package com.example.hotelbooking.data.network.impl

import com.example.hotelbooking.data.HotelDto
import com.example.hotelbooking.data.network.HotelsApiService
import com.example.hotelbooking.data.network.NetworkClient
import com.example.hotelbooking.data.network.dto.BookingInfoDto
import com.example.hotelbooking.data.network.dto.RoomDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientImpl : NetworkClient {

    private val baseUrl = "https://run.mocky.io"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: HotelsApiService by lazy {
        retrofit.create(HotelsApiService::class.java)
    }

    override suspend fun getHotelInfo(): List<HotelDto> {
        val response = retrofitService.getHotelInfo()

        return if (response.code() == 200 && response.body() != null) {
            listOf(response.body()!!)
        } else emptyList()

    }

    override suspend fun getRoomsInfo(): List<RoomDto> {
        val response = retrofitService.getRoomsInfo()

        return if (response.code() == 200) {
            response.body()?.rooms ?: emptyList()
        } else emptyList()
    }

    override suspend fun getBookingInfo(): List<BookingInfoDto> {
        val response = retrofitService.getBookingInfo()

        return if (response.code() == 200 && response.body() != null) {
            listOf(response.body()!!)
        } else emptyList()
    }
}
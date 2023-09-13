package com.example.hotelbooking.di

import com.example.hotelbooking.data.converters.NetworkConverter
import com.example.hotelbooking.data.network.NetworkClient
import com.example.hotelbooking.data.network.impl.NetworkClientImpl
import com.example.hotelbooking.data.repositories.HotelRepositoryImpl
import com.example.hotelbooking.domain.repositories.HotelRepository
import org.koin.dsl.module

val dataModule = module {

    single<NetworkConverter> { NetworkConverter() }

    single<NetworkClient> { NetworkClientImpl() }

    single<HotelRepository> { HotelRepositoryImpl(networkConverter = get(), networkClient = get()) }
}
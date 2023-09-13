package com.example.hotelbooking.presentation.hotel.view_model

sealed interface ScreenState{
    object Loading: ScreenState
    object Done: ScreenState
    object Error: ScreenState
}
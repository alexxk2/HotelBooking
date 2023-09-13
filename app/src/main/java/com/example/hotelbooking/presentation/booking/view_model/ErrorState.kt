package com.example.hotelbooking.presentation.booking.view_model

sealed interface ErrorState{
    object NotError: ErrorState
    object EmptyPhone: ErrorState
    object EmptyEmail: ErrorState
    object InvalidEmail: ErrorState
    object InvalidPhone: ErrorState
    object PhoneEmailAreEmpty: ErrorState
    object Default: ErrorState
    object TouristInfoEmpty: ErrorState
}
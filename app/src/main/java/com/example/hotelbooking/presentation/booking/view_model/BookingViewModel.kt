package com.example.hotelbooking.presentation.booking.view_model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.booking.GetBookingInfoUseCase
import com.example.hotelbooking.domain.models.BookingInfo
import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.presentation.hotel.view_model.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookingViewModel(
    private val getBookingInfoUseCase: GetBookingInfoUseCase
) : ViewModel() {


    private val _bookingInfo = MutableLiveData<BookingInfo>()
    val bookingInfo: LiveData<BookingInfo> = _bookingInfo

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _errorState = MutableLiveData<ErrorState>()
    val errorState: LiveData<ErrorState> = _errorState


    fun getBookingInfo() {
        _screenState.value = ScreenState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResult = getBookingInfoUseCase.execute()
                _bookingInfo.postValue(apiResult.first())

                _screenState.postValue(
                    if (apiResult.isNotEmpty()) {
                        ScreenState.Done
                    } else ScreenState.Error
                )

            } catch (e: Exception) {
                _screenState.postValue(ScreenState.Error)
            }
        }
    }

    fun isInputValid(stringPhone: Editable?, stringEmail: Editable?) {
        if (stringPhone.isNullOrBlank() && stringEmail.isNullOrBlank()) {
            _errorState.value = ErrorState.BothEmpty
        } else if (stringPhone.isNullOrBlank()) {
            _errorState.value = ErrorState.EmptyPhone
        } else if (stringEmail.isNullOrBlank()) {
            _errorState.value = ErrorState.EmptyEmail
        } else if (!isEmailValid(stringEmail.toString())) {
            _errorState.value = ErrorState.InvalidEmail
        } else if (!isPhoneValid(stringPhone.toString())) {
            _errorState.value = ErrorState.InvalidPhone
        } else _errorState.value = ErrorState.NotError
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 18
    }

}
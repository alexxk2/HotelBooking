package com.example.hotelbooking.presentation.booking.view_model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.booking.GetBookingInfoUseCase
import com.example.hotelbooking.domain.models.BookingInfo
import com.example.hotelbooking.presentation.hotel.view_model.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    private var touristFieldsAreNotEmpty = false

    private var isBirthdayEmpty = true
    private var isFirstNameEmpty = true
    private var isLastNameEmpty = true
    private var isCitizenshipEmpty = true
    private var isPassportEmpty = true
    private var isPassportDateEmpty = true

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
            _errorState.value = ErrorState.PhoneEmailAreEmpty
        } else if (stringPhone.isNullOrBlank()) {
            _errorState.value = ErrorState.EmptyPhone
        } else if (stringEmail.isNullOrBlank()) {
            _errorState.value = ErrorState.EmptyEmail
        } else if (!isEmailValid(stringEmail.toString())) {
            _errorState.value = ErrorState.InvalidEmail
        } else if (!isPhoneValid(stringPhone.toString())) {
            _errorState.value = ErrorState.InvalidPhone
        } else if (!touristFieldsAreNotEmpty) {
            _errorState.value = ErrorState.TouristInfoEmpty
        } else _errorState.value = ErrorState.NotError

        viewModelScope.launch {
            delay(100L)
            _errorState.value = ErrorState.Default
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 18
    }


    fun checkIfBirthdayEmpty(s: CharSequence?) {
        isBirthdayEmpty = s.isNullOrBlank()
        checkIfAllFieldsEmpty()
    }

    fun checkIfCitizenshipEmpty(s: CharSequence?) {
        isCitizenshipEmpty = s.isNullOrBlank()
        checkIfAllFieldsEmpty()
    }

    fun checkIfFirstNameEmpty(s: CharSequence?) {
        isFirstNameEmpty = s.isNullOrBlank()
        checkIfAllFieldsEmpty()
    }

    fun checkIfLastNameEmpty(s: CharSequence?) {
        isLastNameEmpty = s.isNullOrBlank()
        checkIfAllFieldsEmpty()
    }

    fun checkIfPassportEmpty(s: CharSequence?) {
        isPassportEmpty = s.isNullOrBlank()
        checkIfAllFieldsEmpty()
    }

    fun checkIfPassportDateEmpty(s: CharSequence?) {
        isPassportDateEmpty = s.isNullOrBlank()
        checkIfAllFieldsEmpty()
    }

    private fun checkIfAllFieldsEmpty(){
        touristFieldsAreNotEmpty =
            !isBirthdayEmpty && !isCitizenshipEmpty && !isFirstNameEmpty && !isLastNameEmpty && !isPassportEmpty && !isPassportDateEmpty
    }
}
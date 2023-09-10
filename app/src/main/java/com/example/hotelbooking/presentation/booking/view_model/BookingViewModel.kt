package com.example.hotelbooking.presentation.booking.view_model

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
}
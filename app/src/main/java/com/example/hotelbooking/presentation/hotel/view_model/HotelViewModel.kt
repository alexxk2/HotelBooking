package com.example.hotelbooking.presentation.hotel.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.hotel.GetHotelInfoUseCase
import com.example.hotelbooking.domain.models.Hotel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelViewModel(
    private val getHotelInfoUseCase: GetHotelInfoUseCase
) : ViewModel() {

    private val _hotel = MutableLiveData<Hotel>()
    val hotel: LiveData<Hotel> = _hotel

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState


    fun getHotelInfo() {
        _screenState.value = ScreenState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResult = getHotelInfoUseCase.execute()
                _hotel.postValue(apiResult.first())

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
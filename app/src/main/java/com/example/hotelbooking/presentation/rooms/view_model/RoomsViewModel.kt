package com.example.hotelbooking.presentation.rooms.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.models.Room
import com.example.hotelbooking.domain.rooms.GetRoomsInfoUseCase
import com.example.hotelbooking.presentation.hotel.view_model.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val getRoomsInfoUseCase: GetRoomsInfoUseCase
): ViewModel() {

    private val _room = MutableLiveData<List<Room>>()
    val room: LiveData<List<Room>> = _room

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    fun getRoomsInfo(){

        _screenState.value = ScreenState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResult = getRoomsInfoUseCase.execute()
                _room.postValue(apiResult)

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
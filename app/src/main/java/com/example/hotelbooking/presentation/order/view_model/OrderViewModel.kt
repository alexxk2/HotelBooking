package com.example.hotelbooking.presentation.order.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class OrderViewModel : ViewModel() {

    private val _randomOrder = MutableLiveData<Int>()
    val randomOrder: LiveData<Int> = _randomOrder

    init {
        _randomOrder.value = Random.nextInt(from = 100_000, until = 200_000)
    }
}
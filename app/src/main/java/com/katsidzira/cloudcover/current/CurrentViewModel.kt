package com.katsidzira.cloudcover.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katsidzira.cloudcover.network.WeatherResponse
import com.katsidzira.cloudcover.network.WeatherService
import kotlinx.coroutines.launch

class CurrentViewModel : ViewModel() {

    private val _weatherData by lazy { MutableLiveData<WeatherResponse>() }
    val weatherData: LiveData<WeatherResponse> = _weatherData

    private val _zipCode by lazy { MutableLiveData<String>() }
    val zipCode: LiveData<String> = _zipCode

    private val weatherService = WeatherService()

    fun getCurrentWeather(zip: String) {
        viewModelScope.launch {
            _weatherData.value = weatherService.fetchCurrentWeather(zip)
        }
    }

    fun enterZipCode(zip: String) {
        _zipCode.value = zip
    }
}
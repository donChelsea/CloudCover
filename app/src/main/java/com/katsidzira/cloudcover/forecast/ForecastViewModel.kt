package com.katsidzira.cloudcover.forecast

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katsidzira.cloudcover.network.ForecastResponse
import com.katsidzira.cloudcover.network.WeatherService
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val _forecastData by lazy { MutableLiveData<ForecastResponse>() }
    val forecastData: LiveData<ForecastResponse> = _forecastData

    private val _zipCode by lazy { MutableLiveData<String>() }
    val zipCode: LiveData<String> = _zipCode

    var days = 5

    private val weatherService = WeatherService()

    fun getForecast(zip: String, days: Int) {
        viewModelScope.launch {
            _forecastData.value = weatherService.fetchForecast(zip, days)
        }
    }

    fun enterZipCode(zip: String) {
        _zipCode.value = zip
    }
}
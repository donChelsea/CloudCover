package com.katsidzira.cloudcover.forecast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.katsidzira.cloudcover.network.ForecastResponse
import com.katsidzira.cloudcover.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel: ViewModel() {
    private val _weatherData by lazy { MutableLiveData<ForecastResponse>() }
    val weatherData: LiveData<ForecastResponse> = _weatherData

    private val weatherService = WeatherService()

    fun getForecast(zip: String, days: Int) {
        weatherService.fetchForecast(zip, days).enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                Log.d(
                    "WeatherService",
                    "forecast days: ${response.body()?.forecast?.forecastDay?.size}"
                )
                _weatherData.value = response.body()
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("WeatherService", "${t.message}")
            }
        })
}}
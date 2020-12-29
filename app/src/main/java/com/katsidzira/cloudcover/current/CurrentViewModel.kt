package com.katsidzira.cloudcover.current

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.katsidzira.cloudcover.network.WeatherResponse
import com.katsidzira.cloudcover.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentViewModel: ViewModel() {

    private val _weatherData by lazy { MutableLiveData<WeatherResponse>() }
    val weatherData: LiveData<WeatherResponse> = _weatherData

    private val weatherService = WeatherService()

    fun getCurrentWeather(zip: String) {
        weatherService.fetchCurrentWeather(zip).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                Log.d("CurrentViewModel", "town: ${response.body()?.location?.name}")
                Log.d("CurrentViewModel", "wind: ${response.body()?.current?.windSpeed}")
                _weatherData.value = response.body()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("CurrentViewModel", "${t.message}")
            }
        })
    }
}
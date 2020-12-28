package com.katsidzira.cloudcover.network

import android.util.Log
import com.katsidzira.cloudcover.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    fun fetchCurrentWeather(zip: String) {
        weatherService.getCurrentWeather(key, zip).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                Log.d("WeatherService", "town: ${response.body()?.location?.name}")
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("WeatherService", "${t.message}")
            }
        })
    }

    fun fetchForecast(zip: String, days: Int) {
        weatherService.getForecast(key, zip, days).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                Log.d(
                    "WeatherService",
                    "forecast days: ${response.body()?.forecast?.forecastDay?.size}"
                )
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("WeatherService", "${t.message}")
            }
        })
    }

    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1/"

        private const val key = "f47000bc7cb04a32958205217202812"

        private val weatherService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

    }
}
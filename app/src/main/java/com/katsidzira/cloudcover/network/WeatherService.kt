package com.katsidzira.cloudcover.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    fun fetchCurrentWeather(zip: String): Call<WeatherResponse> {
        return weatherService.getCurrentWeather(key, zip)
    }

    fun fetchForecast(zip: String, days: Int): Call<ForecastResponse> {
        return weatherService.getForecast(key, zip, days)
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
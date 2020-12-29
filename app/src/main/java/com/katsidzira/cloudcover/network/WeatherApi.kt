package com.katsidzira.cloudcover.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") zip: String
    ): Call<WeatherResponse>

    @GET("forecast.json")
    fun getForecast(
        @Query("key") key: String,
        @Query("q") zip: String,
        @Query("days") days: Int
    ): Call<ForecastResponse>
}
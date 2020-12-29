package com.katsidzira.cloudcover.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") zip: String
    ): WeatherResponse

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") zip: String,
        @Query("days") days: Int
    ): ForecastResponse
}
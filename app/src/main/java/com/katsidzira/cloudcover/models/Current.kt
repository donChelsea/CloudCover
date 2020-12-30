package com.katsidzira.cloudcover.models

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("temp_f")
    val temp: String,

    val condition: WeatherCondition,

    @SerializedName("wind_mph")
    val windSpeed: String,

    val humidity: String,

    @SerializedName("feelslike_f")
    val feelsLike: String
)

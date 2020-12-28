package com.katsidzira.cloudcover.models

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("maxtemp_f")
    val maxTemp: String,

    @SerializedName("mintemp_f")
    val minTemp: String,

    @SerializedName("condition")
    val weatherCondition: WeatherCondition
)
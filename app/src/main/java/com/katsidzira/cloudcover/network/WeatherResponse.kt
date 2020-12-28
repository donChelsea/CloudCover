package com.katsidzira.cloudcover.network

import com.katsidzira.cloudcover.models.Current
import com.katsidzira.cloudcover.models.Forecast
import com.katsidzira.cloudcover.models.Location

data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)
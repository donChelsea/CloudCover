package com.katsidzira.cloudcover.network

import com.katsidzira.cloudcover.models.Forecast
import com.katsidzira.cloudcover.models.Location

data class ForecastResponse(
    val location: Location,
    val forecast: Forecast
)
package com.katsidzira.cloudcover.models

import com.google.gson.annotations.SerializedName

data class Location(
    val name: String,
    @SerializedName("localtime")
    val datestamp: String
)

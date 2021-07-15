package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherImageInfo (
    @SerializedName("icon")
    val icon: String,
)
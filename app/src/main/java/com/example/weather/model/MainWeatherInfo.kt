package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class MainWeatherInfo (
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int
)
package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class HourlyWeatherInfo (
    @SerializedName("dt")
    val timestamp: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("weather")
    val weather: List<WeatherImageInfo>
)
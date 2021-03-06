package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherInfo (
    @SerializedName("dt")
    val timestamp: String?,
    @SerializedName("temp")
    val temp: DailyWeatherTempInfo,
    @SerializedName("weather")
    val weather: List<WeatherImageInfo>
)


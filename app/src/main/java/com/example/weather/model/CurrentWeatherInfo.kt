package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherInfo(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("weather")
    val weather: List<WeatherImageInfo>
)
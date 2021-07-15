package com.example.weather.model

data class CurrentWeatherResponse(
    val main: MainWeatherInfo,
    val weather: List<WeatherImageInfo>
)
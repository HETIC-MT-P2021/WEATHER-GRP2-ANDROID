package com.example.weather.model

data class WeatherInfoResponse(
    val current: CurrentWeatherInfo,
    val hourly: List<HourlyWeatherInfo>,
    val daily: List<DailyWeatherInfo>,
)
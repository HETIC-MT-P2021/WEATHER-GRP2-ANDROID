package com.example.weather.model

data class HourWeather(
    val id: Int,
    val hour: String,
    val temp: String,
    val imageSrc: String
)
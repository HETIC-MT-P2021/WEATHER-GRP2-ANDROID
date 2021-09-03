package com.example.weather.model

import com.google.gson.annotations.SerializedName

class DailyWeatherTempInfo (
    @SerializedName("day")
    val day: Double,
)
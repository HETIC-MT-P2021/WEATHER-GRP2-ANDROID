package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherTempInfo (
    @SerializedName("day")
    val day: Double,

    @SerializedName("morn")
    val morning: Double,
)


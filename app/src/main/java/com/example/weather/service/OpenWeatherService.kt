package com.example.weather.service

import com.example.weather.model.OpenWeatherResponse
import retrofit2.Response
import retrofit2.http.GET

interface OpenWeatherService {
    @GET("forecast?q=Paris,fr&lang=fr&units=metric&appid=www")
    suspend fun getWeather(): Response<OpenWeatherResponse>
}
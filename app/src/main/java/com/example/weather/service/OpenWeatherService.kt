package com.example.weather.service

import com.example.weather.model.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather?q=Paris")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Response<CurrentWeatherResponse>
}
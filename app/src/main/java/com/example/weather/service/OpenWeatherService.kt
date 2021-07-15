package com.example.weather.service

import com.example.weather.model.WeatherInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("onecall")
    suspend fun getWeatherInfo(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") languageCode: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Response<WeatherInfoResponse>
}
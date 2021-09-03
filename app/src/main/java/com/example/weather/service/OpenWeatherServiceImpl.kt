package com.example.weather.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.weather.model.WeatherInfoResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OpenWeatherServiceImpl {
    companion object {
        const val baseUrl = "https://api.openweathermap.org/data/2.5/"
    }

    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
        }

    private fun getRetrofit(): Retrofit {
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            callTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getWeatherInfo(latitude: Double, longitude: Double, languageCode: String, appId: String): Response<WeatherInfoResponse> =
        getRetrofit().create(OpenWeatherService::class.java).getWeatherInfo(latitude,
            longitude, languageCode,"minutely", "metric", appId)
}
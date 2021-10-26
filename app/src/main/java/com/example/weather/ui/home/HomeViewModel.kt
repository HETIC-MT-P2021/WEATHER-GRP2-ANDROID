package com.example.weather.ui.home

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.model.*
import com.example.weather.service.OpenWeatherServiceImpl
import com.example.weather.util.Convert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val openWeatherService by lazy {
        OpenWeatherServiceImpl()
    }

    private var currentWeatherIcon: MutableLiveData<String> = MutableLiveData()
    fun getCurrentWeatherIcon(): MutableLiveData<String> {
        return currentWeatherIcon;
    }

    private var currentWeatherTemp: MutableLiveData<String> = MutableLiveData()
    fun getCurrentWeatherTemp(): MutableLiveData<String> {
        return currentWeatherTemp;
    }

    private var currentWeatherDescription: MutableLiveData<String> = MutableLiveData()
    fun getCurrentWeatherDescription(): MutableLiveData<String> {
        return currentWeatherDescription;
    }

    private var currentWeatherAfternoonAndMorning: MutableLiveData<String> = MutableLiveData()
    fun getCurrentWeatherAfternoonAndMorning(): MutableLiveData<String> {
        return currentWeatherAfternoonAndMorning;
    }

    private var recyclerViewHours: MutableLiveData<List<HourlyWeatherInfo>> = MutableLiveData()
    fun getRecyclerHours(): MutableLiveData<List<HourlyWeatherInfo>> {
        return recyclerViewHours;
    }

    private var recyclerViewDaily: MutableLiveData<List<DailyWeatherInfo>> = MutableLiveData()
    fun getRecyclerDaily(): MutableLiveData<List<DailyWeatherInfo>> {
        return recyclerViewDaily;
    }

    private val context: Context = application.applicationContext
    fun loadCurrentWeather(latitude: Double, longitude: Double) {
        if (!openWeatherService.isNetworkAvailable(context)) {
            displayError("Network not available")
            return
        }

        val ai: ApplicationInfo = context.packageManager
            .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
        val appId = ai.metaData["openWeatherAPIKey"] // see AndroidManifest.xml

        viewModelScope.launch {
            val response = openWeatherService.getWeatherInfo(48.85,2.35,"fr", appId.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val weatherInfo = response.body()?.current?.weather?.get(0)
                    currentWeatherIcon.postValue(weatherInfo?.icon)

                    currentWeatherTemp.postValue(response.body()?.current?.temp?.let {
                        Convert.doubleToTemp(
                            it
                        )
                    })

                    currentWeatherDescription.postValue(weatherInfo?.description?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })

                    val todayTemp = getCurrentTempsAfternoonAndMorning(response.body()?.daily?.get(0)?.temp)
                    currentWeatherAfternoonAndMorning.postValue(todayTemp)

                    recyclerViewHours.postValue(response.body()?.hourly!!)

                    recyclerViewDaily.postValue(response.body()?.daily!!)
                } else {
                    displayError("Error loading data weather from API")
                }
            }
        }
    }

    private fun getCurrentTempsAfternoonAndMorning(currentWeatherInfo: DailyWeatherTempInfo?): String {
        val currentWeatherAfternoon = currentWeatherInfo?.day?.let {
            Convert.doubleToTemp(
                it
            )
        }
        val currentWeatherMorning = currentWeatherInfo?.morning?.let {
            Convert.doubleToTemp(
                it
            )
        }
        return "$currentWeatherAfternoon/$currentWeatherMorning"
    }

    private fun displayError(message: String) {
        Toast.makeText(getApplication<Application>().applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
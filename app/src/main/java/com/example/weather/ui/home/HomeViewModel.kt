package com.example.weather.ui.home

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.adapter.HoursWeatherAdapter
import com.example.weather.model.*
import com.example.weather.service.OpenWeatherService
import com.example.weather.service.OpenWeatherServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val openWeatherService by lazy {
        OpenWeatherServiceImpl()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Ensoleillé"
    }
    val text: LiveData<String> = _text

    lateinit var recyclerViewHours: MutableLiveData<List<HourlyWeatherInfo>>
    fun getRecyclerHours(): MutableLiveData<List<HourlyWeatherInfo>> {
        return recyclerViewHours;
    }

    lateinit var recyclerViewDaily: MutableLiveData<List<DailyWeatherInfo>>
    fun getRecyclerDaily(): MutableLiveData<List<DailyWeatherInfo>> {
        return recyclerViewDaily;
    }

    init {
        recyclerViewHours = MutableLiveData()
        recyclerViewDaily = MutableLiveData()
    }

    private fun displayError(message: String) {
        Toast.makeText(getApplication<Application>().applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private val context: Context = application.applicationContext

    fun loadCurrentWeather() {
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
                    recyclerViewHours.postValue(response.body()?.hourly!!)
                    recyclerViewDaily.postValue(response.body()?.daily!!)

                    //CurrentWeatherData = response.body()?.current!!
                } else {
                    displayError("Error loading data weather from API")
                }
            }
        }
    }
}
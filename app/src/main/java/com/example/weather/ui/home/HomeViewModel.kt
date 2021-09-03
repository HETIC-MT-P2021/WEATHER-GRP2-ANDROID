package com.example.weather.ui.home

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.adapter.HoursWeatherAdapter
import com.example.weather.model.*
import com.example.weather.service.OpenWeatherServiceImpl
import com.example.weather.util.DateFormatHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Ensoleillé"
    }

    var DailyWeatherData : List<DailyWeatherInfo> = listOf()
    var HoursWeatherData : List<HourlyWeatherInfo> = listOf()
    lateinit var CurrentWeatherData : CurrentWeatherInfo

    val text: LiveData<String> = _text

    private lateinit var recyclerAdapterHour: HoursWeatherAdapter

    private val _recyclerViewHours = MutableLiveData<List<HourlyWeatherInfo>>().apply {
        value = HoursWeatherData
    }
    val recyclerViewHours: LiveData<List<HourlyWeatherInfo>> = _recyclerViewHours

    private val _recyclerViewDaily = MutableLiveData<List<DailyWeatherInfo>>().apply {
        value = DailyWeatherData
    }
    val recyclerViewDaily: LiveData<List<DailyWeatherInfo>> = _recyclerViewDaily

    private fun displayError(message: String) {
        Toast.makeText(getApplication<Application>().applicationContext, message, Toast.LENGTH_LONG).show()
    }


}
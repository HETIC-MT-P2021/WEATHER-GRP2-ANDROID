package com.example.weather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.DailyWeatherInfo
import com.example.weather.model.DailyWeatherTempInfo
import com.example.weather.model.HourWeather
import com.example.weather.model.WeatherImageInfo
import com.example.weather.util.DateFormatHelper
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ensoleillé"
    }
    val text: LiveData<String> = _text

    private val _recyclerViewHours = MutableLiveData<ArrayList<HourWeather>>().apply {
        val hours: ArrayList<HourWeather> = ArrayList()
        hours.add(HourWeather(1, "9AM","35°", "image1"))
        hours.add(HourWeather(2, "10AM", "19°", "image2"))
        hours.add(HourWeather(3, "11AM ","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        hours.add(HourWeather(3, "11AM","5°", "image3"))
        value = hours
    }
    val recyclerViewHours: LiveData<ArrayList<HourWeather>> = _recyclerViewHours

    private val _recyclerViewDaily = MutableLiveData<ArrayList<DailyWeatherInfo>>().apply {
        val hours: ArrayList<DailyWeatherInfo> = ArrayList()

        val ts = "1631271600"
        val date: String = DateFormatHelper.getDayOfWeek(ts)

        hours.add(DailyWeatherInfo(date, DailyWeatherTempInfo(20.00), WeatherImageInfo("test")))
        hours.add(DailyWeatherInfo(date, DailyWeatherTempInfo(12.00), WeatherImageInfo("test")))
        hours.add(DailyWeatherInfo(date, DailyWeatherTempInfo(14.00), WeatherImageInfo("test")))
        hours.add(DailyWeatherInfo(date, DailyWeatherTempInfo(9.00), WeatherImageInfo("test")))
        hours.add(DailyWeatherInfo(date, DailyWeatherTempInfo(27.00), WeatherImageInfo("test")))
        value = hours
    }
    val recyclerViewDaily: LiveData<ArrayList<DailyWeatherInfo>> = _recyclerViewDaily

}
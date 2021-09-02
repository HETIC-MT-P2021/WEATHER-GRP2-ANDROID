package com.example.weather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.HourWeather

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ensoleillé"
    }
    val text: LiveData<String> = _text

    private val _recyclerViewHours = MutableLiveData<ArrayList<HourWeather>>().apply {
        var hours: ArrayList<HourWeather> = ArrayList()
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
}
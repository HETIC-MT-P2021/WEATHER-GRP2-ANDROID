package com.example.weather.util

import java.text.SimpleDateFormat
import java.util.*

class Convert {
    companion object {
        @JvmStatic
        fun doubleToTemp(temp: Double): String {
            var tempRounded: Int = temp.toInt()
            return "$tempRounded°"
        }
    }
}
package com.example.weather.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateFormatHelper {
    companion object {
        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        fun getDayOfWeek(timestamp: String): String {
            val sdf = SimpleDateFormat("E") // EEEE for all letters of a day
            Log.d("DEBUGGG", timestamp)
            val date: Date = Date(timestamp)
            return sdf.format(date)
        }
    }
}
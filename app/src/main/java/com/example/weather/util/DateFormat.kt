package com.example.weather.util

import java.text.SimpleDateFormat
import java.util.*

class DateFormatHelper {
    companion object {
        @JvmStatic
        fun getDayOfWeek(timestamp: String): String {
            //TODO: Check if the value returned is right
            val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH) // EEEE for all letters of a day
            val tsLong: Long = timestamp.toLong()
            val date = Date(tsLong)

            return sdf.format(date)
        }
    }
}
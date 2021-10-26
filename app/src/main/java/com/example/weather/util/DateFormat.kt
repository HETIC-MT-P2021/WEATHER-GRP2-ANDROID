package com.example.weather.util

import java.text.SimpleDateFormat
import java.util.*

class DateFormatHelper {
    companion object {
        @JvmStatic
        fun getDayOfWeek(timestamp: String): String {
            val sdf = SimpleDateFormat("E", Locale.FRANCE) // E for 3 first letters
            val tsLong: Long = timestamp.toLong() * 1000L
            val date = Date(tsLong)

            return sdf.format(date)
        }

        @JvmStatic
        fun getHour(timestamp: String): String {
            val sdf = SimpleDateFormat("H", Locale.FRANCE) // EEEE for all letters of a day
            val tsLong: Long = timestamp.toLong() * 1000L
            val date = Date(tsLong)

            return sdf.format(date)
        }
    }
}
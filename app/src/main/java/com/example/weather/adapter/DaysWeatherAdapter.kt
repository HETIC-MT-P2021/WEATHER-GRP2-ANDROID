package com.example.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.RecyclerviewRowDailyBinding
import com.example.weather.model.DailyWeatherInfo
import com.example.weather.util.Convert
import com.example.weather.util.DateFormatHelper
import com.squareup.picasso.Picasso
import java.util.*

class DaysWeatherAdapter (
    private val arrayList: List<DailyWeatherInfo>,
    private val context: Context
) : RecyclerView.Adapter<DaysWeatherAdapter.DaysWeatherViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysWeatherViewHolder {
        val binding = RecyclerviewRowDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaysWeatherViewHolder, position: Int) {
        holder.bind(arrayList[position], position)
    }

    override fun getItemCount(): Int {
        if(arrayList.isEmpty()){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    inner  class DaysWeatherViewHolder(private val binding: RecyclerviewRowDailyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: DailyWeatherInfo, position: Int){
            var currentDay = "Today"
            if (position > 0) {
                currentDay = DateFormatHelper.getDayOfWeek(day.timestamp.toString()).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            binding.theDay.text = currentDay
            binding.dayTemp.text = Convert.doubleToTemp(day.temp.day)
            Picasso.get().load("https://openweathermap.org/img/wn/" + day.weather[0].icon + "@2x.png")
                .resize(200, 200)
                .into(binding.weatherImage)
        }
    }
}
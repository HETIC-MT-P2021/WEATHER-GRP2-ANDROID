package com.example.weather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.RecyclerviewHourBinding
import com.example.weather.databinding.RecyclerviewRowDailyBinding
import com.example.weather.model.DailyWeatherInfo
import com.example.weather.model.HourWeather
import com.squareup.picasso.Picasso

class DaysWeatherAdapter (
    private val arrayList: List<DailyWeatherInfo>,
    private val context: Context
) : RecyclerView.Adapter<DaysWeatherAdapter.DaysWeatherViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysWeatherViewHolder {
        val binding = RecyclerviewRowDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaysWeatherViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        if(arrayList.isEmpty()){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    inner  class DaysWeatherViewHolder(private val binding: RecyclerviewRowDailyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: DailyWeatherInfo){
            binding.theDay.text = day.timestamp.toString()
            binding.dayTemp.text = day.temp.day.toString()
            Picasso.get().load("https://openweathermap.org/img/wn/10d@2x.png")
                .resize(200, 200)
                .into(binding.weatherImage)
        }
    }
}
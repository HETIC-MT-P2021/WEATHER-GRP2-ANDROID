package com.example.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.RecyclerviewHourBinding
import com.example.weather.model.HourWeather
import com.squareup.picasso.Picasso

class HoursWeatherAdapter (
    private val arrayList: ArrayList<HourWeather>,
    private val context: Context
) : RecyclerView.Adapter<HoursWeatherAdapter.HoursWeatherViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursWeatherAdapter.HoursWeatherViewHolder {
        val binding = RecyclerviewHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoursWeatherAdapter.HoursWeatherViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    inner  class HoursWeatherViewHolder(private val binding: RecyclerviewHourBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hour: HourWeather){
            binding.theHour.text = hour.hour
            binding.hourTemp.text = hour.temp
            Picasso.get().load("https://openweathermap.org/img/wn/10d@2x.png")
                .resize(200, 200)
                .into(binding.hourImage)
        }
    }

}
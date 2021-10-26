package com.example.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.RecyclerviewHourBinding
import com.example.weather.model.HourlyWeatherInfo
import com.example.weather.util.Convert
import com.example.weather.util.DateFormatHelper
import com.squareup.picasso.Picasso

class HoursWeatherAdapter (
    private val arrayList: List<HourlyWeatherInfo>,
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
        if(arrayList.isEmpty()){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    inner  class HoursWeatherViewHolder(private val binding: RecyclerviewHourBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hour: HourlyWeatherInfo){
            binding.theHour.text = DateFormatHelper.getHour(hour.timestamp.toString()) + ":00"
            binding.hourTemp.text = Convert.doubleToTemp(hour.temp)
            Picasso.get().load("https://openweathermap.org/img/wn/" + hour.weather[0].icon + "@2x.png")
                .resize(200, 200)
                .into(binding.hourImage)
        }
    }

}
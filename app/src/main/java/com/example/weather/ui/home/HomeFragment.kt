package com.example.weather.ui.home

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapter.DaysWeatherAdapter
import com.example.weather.adapter.HoursWeatherAdapter
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.model.CurrentWeatherInfo
import com.example.weather.model.DailyWeatherInfo
import com.example.weather.model.HourWeather
import com.example.weather.model.HourlyWeatherInfo
import com.example.weather.service.OpenWeatherServiceImpl
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var rvAdapterHours: HoursWeatherAdapter
    lateinit var rvAdapterDaily: DaysWeatherAdapter
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        bindValueToXMLElement(container)

        homeViewModel.loadCurrentWeather()
        return root
    }

    /**
     *  Bind values to activity elements, it allows to get data from API and
     *  update dynamically values
     */
    private fun bindValueToXMLElement(container: ViewGroup?) {
        val currentWeatherIcon: ImageView = binding.currentWeatherIcon
        homeViewModel.getCurrentWeatherIcon().observe(viewLifecycleOwner, Observer {
            Picasso.get().load("https://openweathermap.org/img/wn/$it@2x.png")
                .resize(400, 400)
                .into(currentWeatherIcon)
        })

        val currentWeatherTemp: TextView = binding.currentWeatherTemp
        homeViewModel.getCurrentWeatherTemp().observe(viewLifecycleOwner, Observer {
            currentWeatherTemp.text = it
        })

        val currentWeatherDesc: TextView = binding.weather
        homeViewModel.getCurrentWeatherDescription().observe(viewLifecycleOwner, Observer {
            currentWeatherDesc.text = it
        })

        val currentWeatherAfternoonAndMorning: TextView = binding.currentWeatherAfternoonAndMorning
        homeViewModel.getCurrentWeatherAfternoonAndMorning().observe(viewLifecycleOwner, Observer {
            currentWeatherAfternoonAndMorning.text = it
        })

        val rvHours: RecyclerView = binding.hoursWeatherRecyclerView
        homeViewModel.getRecyclerHours().observe(viewLifecycleOwner, Observer {
            if (container != null) {
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = RecyclerView.HORIZONTAL
                rvHours.layoutManager = layoutManager
                rvAdapterHours = HoursWeatherAdapter(it, container.context)
                rvHours.adapter = rvAdapterHours
            }
        })

        val rvDaily: RecyclerView = binding.dailyWeatherRecyclerView
        homeViewModel.getRecyclerDaily().observe(viewLifecycleOwner, Observer {
            if (container != null) {
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = RecyclerView.HORIZONTAL
                rvDaily.layoutManager = layoutManager
                rvAdapterDaily = DaysWeatherAdapter(it, container.context)
                rvDaily.adapter = rvAdapterDaily
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
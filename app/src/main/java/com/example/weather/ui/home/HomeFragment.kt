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

    /*private val openWeatherService by lazy {
        OpenWeatherServiceImpl()
    }*/

    /*private fun loadCurrentWeather() {
        if (!openWeatherService.isNetworkAvailable(requireContext())) {
            displayError("Network not available")
            return
        }

        val ai: ApplicationInfo = requireContext().packageManager
            .getApplicationInfo(requireContext().packageName, PackageManager.GET_META_DATA)
        val appId = ai.metaData["openWeatherAPIKey"] // see AndroidManifest.xml

        CoroutineScope(Dispatchers.IO).launch {
            val response = openWeatherService.getWeatherInfo(48.85,2.35,"fr", appId.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("DEBUG success", response.body().toString());

                    homeViewModel.HoursWeatherData = response.body()?.hourly!!
                    homeViewModel.DailyWeatherData = response.body()?.daily!!
                    homeViewModel.CurrentWeatherData = response.body()?.current!!
                } else {
                    displayError("Error loading data weather from API")
                }
            }
        }
    }*/

    /*private fun displayError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        //loadCurrentWeather()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.weather
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
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

        homeViewModel.loadCurrentWeather()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
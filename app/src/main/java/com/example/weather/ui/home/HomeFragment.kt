package com.example.weather.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapter.DaysWeatherAdapter
import com.example.weather.adapter.HoursWeatherAdapter
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.model.HourWeather

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
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.weather

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val rvHours: RecyclerView = binding.hoursWeatherRecyclerView
        homeViewModel.recyclerViewHours.observe(viewLifecycleOwner, Observer {
            if (container != null) {
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = RecyclerView.HORIZONTAL
                rvHours.layoutManager = layoutManager
                rvAdapterHours = HoursWeatherAdapter(it, container.context)
                rvHours.adapter = rvAdapterHours
            }
        })

        val rvDaily: RecyclerView = binding.dailyWeatherRecyclerView
        homeViewModel.recyclerViewDaily.observe(viewLifecycleOwner, Observer {
            if (container != null) {
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = RecyclerView.HORIZONTAL
                rvDaily.layoutManager = layoutManager
                rvAdapterDaily = DaysWeatherAdapter(it, container.context)
                rvDaily.adapter = rvAdapterDaily
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
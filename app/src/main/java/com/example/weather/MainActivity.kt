package com.example.weather

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.adapter.HoursWeatherAdapter
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.service.OpenWeatherServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerAdapterHour: HoursWeatherAdapter

    private val openWeatherService by lazy {
        OpenWeatherServiceImpl()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCurrentWeather()
    }

    private fun loadCurrentWeather() {
        if (!openWeatherService.isNetworkAvailable(this)) {
            displayError("Network not available")
            return
        }

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val appId = ai.metaData["openWeatherAPIKey"] // see AndroidManifest.xml

        CoroutineScope(Dispatchers.IO).launch {
            val response = openWeatherService.getCurrentWeather("Paris,fr", "fr", appId.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    displayError(response.body()?.main?.temp.toString())
                } else {
                    displayError("Error loading data weather from API")
                }
            }
        }
    }
    
    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
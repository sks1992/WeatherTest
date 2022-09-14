package sk.sandeep.testapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.testapp.R
import sk.sandeep.testapp.databinding.ActivityWeatherDataBinding
import sk.sandeep.testapp.util.Resource
import sk.sandeep.testapp.view_model.WeatherViewModel


@AndroidEntryPoint
class WeatherDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherDataBinding
    private val weatherViewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_data)

        // val intent = getIntent()
        val intent = intent
        val lat = intent.getStringExtra("lat")!!.toDouble()
        val lon = intent.getStringExtra("lon")!!.toDouble()
        weatherViewModel.showWeatherData(lat,lon)
        bindObservers()
    }

    private fun bindObservers() {
        weatherViewModel.getWeatherData.observe(this) { weatherData ->

            binding.progressBar.visibility = View.GONE
            when (weatherData) {
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, weatherData.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvCity.text =weatherData.data!!.timezone
                    println()
                }

            }
        }
    }
}
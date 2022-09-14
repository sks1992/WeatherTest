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

        val lat = intent.extras!!.getDouble("lat")
        val lon = intent.extras!!.getDouble("lon")

        weatherViewModel.showWeatherData(lat, lon)
        //weatherViewModel.showWeatherData(33.44, -94.04)

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
                    ("Timezone :: " + weatherData.data!!.timezone).also { binding.tvCity.text = it }
                    ("Date Time :: " + weatherData.data.current.dt.toString()).also {
                        binding.currentDateTime.text = it
                    }
                    ("Temperature :: " + weatherData.data.current.temp.toString()).also {
                        binding.currentTemp.text = it
                    }
                    ("Humidity :: " + weatherData.data.current.humidity.toString()).also {
                        binding.currentHumidity.text = it
                    }
                    ("WindSpeed :: " + weatherData.data.current.wind_speed.toString()).also {
                        binding.currentWindSpeed.text = it
                    }

                    ("Date Time :: " + weatherData.data.hourly[0].dt.toString()).also {
                        binding.hourlyDateTime.text = it
                    }
                    ("Temperature :: " + weatherData.data.hourly[0].temp.toString()).also {
                        binding.hourlyTemp.text = it
                    }
                    ("Humidity :: " + weatherData.data.hourly[0].humidity.toString()).also {
                        binding.hourlyHumidity.text = it
                    }
                    ("WindSpeed :: " + weatherData.data.hourly[0].wind_speed.toString()).also {
                        binding.hourlyWindSpeed.text = it
                    }


                    ("Date Time :: " + weatherData.data.daily[0].dt.toString()).also {
                        binding.dailyDateTime.text = it
                    }
                    ("Temperature :: " + weatherData.data.daily[0].temp.day.toString()).also {
                        binding.dailyTemp.text = it
                    }
                    ("Humidity :: " + weatherData.data.daily[0].humidity.toString()).also {
                        binding.dailyHumidity.text = it
                    }
                    ("WindSpeed :: " + weatherData.data.daily[0].wind_speed.toString()).also {
                        binding.dailyWindSpeed.text = it
                    }
                }
            }
        }
    }
}
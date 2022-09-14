package sk.sandeep.testapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.testapp.R
import sk.sandeep.testapp.databinding.ActivityWeatherBinding


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)

        binding.btnSubmit.setOnClickListener {
            val i = Intent(this, WeatherDataActivity::class.java)
            i.putExtra("lat", binding.etLat.text)
            i.putExtra("lon", binding.etLon.text)
            startActivity(i)
        }
    }
}
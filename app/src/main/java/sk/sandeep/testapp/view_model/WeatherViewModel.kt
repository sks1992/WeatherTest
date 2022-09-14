package sk.sandeep.testapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sk.sandeep.testapp.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
//    val lat: MutableLiveData<Double> by lazy { MutableLiveData<Double>() }
//    val lon: MutableLiveData<Double> by lazy { MutableLiveData<Double>() }

    val getWeatherData get() = weatherRepository.weatherData

    fun showWeatherData(lat: Double, lon: Double) {
        viewModelScope.launch {
            weatherRepository.getWeatherData(lat, lon)
        }
    }
}
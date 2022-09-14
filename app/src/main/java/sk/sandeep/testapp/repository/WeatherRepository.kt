package sk.sandeep.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import sk.sandeep.testapp.model.WeatherApiResponse
import sk.sandeep.testapp.network.WeatherApi
import sk.sandeep.testapp.util.Resource

import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    private val _weatherData = MutableLiveData<Resource<WeatherApiResponse>>()
    val weatherData: LiveData<Resource<WeatherApiResponse>>
        get() = _weatherData

    suspend fun getWeatherData(let: Double, lon: Double) {
        _weatherData.postValue(Resource.Loading())
        val response = weatherApi.getWeatherData(let, lon)
        if (response.isSuccessful && response.body() != null) {
            _weatherData.postValue(Resource.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _weatherData.postValue(Resource.Error(errorObj.getString("message")))
        } else {
            _weatherData.postValue(Resource.Error("Something went wrong"))
        }
    }
}
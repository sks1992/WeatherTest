package sk.sandeep.testapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sk.sandeep.testapp.model.WeatherApiResponse
import sk.sandeep.testapp.util.API_KEY

interface WeatherApi {

    @GET("onecall")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY
    ): Response<WeatherApiResponse>
}
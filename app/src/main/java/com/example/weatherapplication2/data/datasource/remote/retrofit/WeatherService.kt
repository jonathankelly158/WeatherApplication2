package com.example.weatherapplication2.data.datasource.remote.retrofit

import com.example.weatherapplication2.data.datasource.remote.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY: String = "abbb31d2ebab870c788567a3e88c39e6"

interface WeatherService {
    @GET("/data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appId") apiKey: String = API_KEY,
        @Query("units") units: String = "imperial"
    ): Response<WeatherResponse>
}

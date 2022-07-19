package com.example.weatherapplication2.data.repository

import com.example.weatherapplication2.data.datasource.remote.retrofit.WeatherService
import com.example.weatherapplication2.data.utils.NetworkState
import com.example.weatherapplication2.domain.mapper.toWeatherList
import com.example.weatherapplication2.domain.models.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepositoryInterface {

    override suspend fun getWeatherForCity(city: String): NetworkState<List<Weather>> {
        return try {
            val response = weatherService.getWeather(city)
            if (response.isSuccessful && response.body() != null) {
                NetworkState.Success(response.body()!!.list.toWeatherList())
            } else {
                NetworkState.Failure
            }
        } catch (ex: Throwable) {
            NetworkState.Failure
        }
    }
}
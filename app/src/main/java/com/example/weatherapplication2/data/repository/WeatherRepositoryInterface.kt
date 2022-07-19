package com.example.weatherapplication2.data.repository

import com.example.weatherapplication2.data.utils.NetworkState
import com.example.weatherapplication2.domain.models.Weather

interface WeatherRepositoryInterface {
    suspend fun getWeatherForCity(city: String): NetworkState<List<Weather>>
}
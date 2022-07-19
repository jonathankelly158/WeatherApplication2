package com.example.weatherapplication2.domain.mapper

import com.example.weatherapplication2.data.datasource.remote.models.HourlyResponse
import com.example.weatherapplication2.domain.models.Weather

fun List<HourlyResponse>.toWeatherList(): List<Weather> {
    return this.map { hourly ->
        Weather(
            hourly.main.feelsLike,
            hourly.main.temp,
            hourly.weather.firstOrNull()?.main.orEmpty(),
            hourly.weather.firstOrNull()?.description.orEmpty()
        )
    }
}
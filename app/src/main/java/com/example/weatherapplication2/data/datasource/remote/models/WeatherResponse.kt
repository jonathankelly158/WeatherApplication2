package com.example.weatherapplication2.data.datasource.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val cnt: Int?,
    val cod: String?,
    val list: List<HourlyResponse>,
    val message: Int?
)
package com.example.weatherapplication2.data.datasource.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyResponse(
    val clouds: Clouds,
    val dt: Int,
    @Json(name = "dt_txt")val dtTxt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
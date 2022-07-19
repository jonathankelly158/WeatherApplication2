package com.example.weatherapplication2.domain.models

data class Weather(
    val feelLike: Double,
    val temp: Double,
    val main: String,
    val description: String,
)
package com.example.weatherapplication2.data.datasource.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    val pod: String
)
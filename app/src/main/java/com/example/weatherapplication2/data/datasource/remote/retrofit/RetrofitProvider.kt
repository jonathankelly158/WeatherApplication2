package com.example.weatherapplication2.data.datasource.remote.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL: String = "http://api.openweathermap.org/"

class RetrofitProvider {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}
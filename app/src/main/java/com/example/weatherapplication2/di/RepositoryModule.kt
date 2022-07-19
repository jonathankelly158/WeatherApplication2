package com.example.weatherapplication2.di

import com.example.weatherapplication2.data.datasource.remote.retrofit.WeatherService
import com.example.weatherapplication2.data.repository.WeatherRepository
import com.example.weatherapplication2.data.repository.WeatherRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesWeatherRepository(weatherService: WeatherService): WeatherRepositoryInterface {
        return WeatherRepository(weatherService)
    }
}
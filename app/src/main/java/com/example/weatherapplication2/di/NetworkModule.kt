package com.example.weatherapplication2.di

import com.example.weatherapplication2.data.datasource.remote.retrofit.WeatherService
import com.example.weatherapplication2.data.datasource.remote.retrofit.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance(): RetrofitProvider {
        return RetrofitProvider()
    }

    @Provides
    @Singleton
    fun providesPokemonService(retrofitProvider: RetrofitProvider): WeatherService {
        return retrofitProvider.retrofit.create(WeatherService::class.java)
    }

}
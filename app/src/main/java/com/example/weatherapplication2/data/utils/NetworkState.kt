package com.example.weatherapplication2.data.utils

sealed class NetworkState<out T> {
    object Init: NetworkState<Nothing>()
    data class Success<T>(val data: T) : NetworkState<T>()
    object Failure : NetworkState<Nothing>()
}

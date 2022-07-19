package com.example.weatherapplication2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication2.data.repository.WeatherRepository
import com.example.weatherapplication2.data.utils.NetworkState
import com.example.weatherapplication2.domain.models.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state: MutableLiveData<UiState> = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    fun onInput(input: String) {
        _state.value = _state.value?.copy(input = input)
    }

    fun getWeatherForCity() {
        viewModelScope.launch {
            val tmpState = _state.value
            _state.value = tmpState?.copy(isLoading = true)
            if (tmpState?.input?.isBlank() == true) {
                _state.value = tmpState.copy(error = "Empty Input")
                return@launch
            }
            val response = weatherRepository.getWeatherForCity(tmpState?.input.orEmpty())
            _state.value = tmpState?.copy(networkState = response)
        }
    }

    fun onWeatherSelected(weather: Weather) {
        _state.value = _state.value?.copy(selectedWeather = weather)
    }

    fun onToastShown() {
        _state.value = _state.value?.copy(isLoading = false)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val input: String = "",
        val networkState: NetworkState<List<Weather>> = NetworkState.Init,
        val selectedWeather: Weather? = null,
        val error: String? = null
    )
}
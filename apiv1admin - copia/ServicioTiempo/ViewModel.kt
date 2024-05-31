package com.example.apiv1admin.ServicioTiempo


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun fetchWeather(cityName: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = ApiTiempoConexion.weatherService.getCurrentWeather(cityName, apiKey)
                _weather.value = response
            } catch (e: Exception) {
                // Manejo de errores
            }
        }
    }
}

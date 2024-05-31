package com.example.apiv1admin.ServicioTiempo



data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val name: String
)



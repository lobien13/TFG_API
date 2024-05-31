package com.example.apiv1admin.ServicioTiempo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiTiempoConexion {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}

package com.example.apiv1admin
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apiv1admin.Usuarios.UsuariosVer
import com.example.apiv1admin.DatosCalidadAire.VerDatosCalidadAireActivity
import com.example.apiv1admin.Incidencias.IncidenciasActivity
import com.example.apiv1admin.Sistemas.SistemasActivity
import com.example.apiv1admin.ServicioTiempo.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        // Botón "Usuarios"
        val btnUsuarios = findViewById<ImageButton>(R.id.btnUsuarios)
        btnUsuarios.setOnClickListener {
            val intent = Intent(this, UsuariosVer::class.java)
            startActivity(intent)
        }

        // Botón "Incidencias"
        val btnIncidencias = findViewById<ImageButton>(R.id.btnIncidencias)
        btnIncidencias.setOnClickListener {
            val intent = Intent(this, IncidenciasActivity::class.java)
            startActivity(intent)
        }

        // Botón "Datos de Calidad del Aire"
        val btnDatosCalidadAire = findViewById<ImageButton>(R.id.btnDatosCalidadAire)
        btnDatosCalidadAire.setOnClickListener {
            val intent = Intent(this, VerDatosCalidadAireActivity::class.java)
            startActivity(intent)
        }

        // Botón "Sistemas"
        val btnSistemasArduino = findViewById<ImageButton>(R.id.btnSistemasArduino)
        btnSistemasArduino.setOnClickListener {
            val intent = Intent(this, SistemasActivity::class.java)
            startActivity(intent)
        }

        // Observando el tiempo y actualizando la UI
        val cityNameTextView = findViewById<TextView>(R.id.city_name)
        val temperatureTextView = findViewById<TextView>(R.id.temperature)
        val weatherDescriptionTextView = findViewById<TextView>(R.id.weather_description)
        val humidityTextView = findViewById<TextView>(R.id.humidity)

        viewModel.weather.observe(this, Observer { weather ->
            cityNameTextView.text = weather.name
            temperatureTextView.text = "${weather.main.temp}°C"
            weatherDescriptionTextView.text = weather.weather[0].description
            humidityTextView.text = "Humidity: ${weather.main.humidity}%"
        })

        // Llamando a fetchWeather con la ciudad y tu API key
        viewModel.fetchWeather("Madrid", "dcb1835b4f000f43d286c935689e0ac3")
    }
}

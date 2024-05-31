package com.example.apiv1admin.DatosCalidadAire

import DatosCalidadAire
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiv1admin.ApiClient
import com.example.apiv1admin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class InsertarDatosCalidadAireActivity : AppCompatActivity() {

    private lateinit var editTextCo2: EditText
    private lateinit var editTextPm25: EditText
    private lateinit var editTextPm10: EditText
    private lateinit var editTextO3: EditText
    private lateinit var editTextNo2: EditText
    private lateinit var editTextSo2: EditText
    private lateinit var editTextSistemaId: EditText
    private lateinit var buttonEnviar: Button
    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insertar_datos_calidad)

        editTextCo2 = findViewById(R.id.editTextCo2)
        editTextPm25 = findViewById(R.id.editTextPm25)
        editTextPm10 = findViewById(R.id.editTextPm10)
        editTextO3 = findViewById(R.id.editTextO3)
        editTextNo2 = findViewById(R.id.editTextNo2)
        editTextSo2 = findViewById(R.id.editTextSo2)
        editTextSistemaId = findViewById(R.id.editTextSistema)
        buttonEnviar = findViewById(R.id.buttonEnviar)

        buttonEnviar.setOnClickListener {
            if (validarCampos()) {
                CoroutineScope(Dispatchers.Main).launch {
                    crearDatosCalidadAire()
                }
            }
        }
    }

    private suspend fun crearDatosCalidadAire() {
        val sistemaId = try {
            editTextSistemaId.text.toString().toLong()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor, introduce un sistemaId válido", Toast.LENGTH_LONG).show()
            return
        }

        apiClient.obtenerSistemaArduinoPorId(
            sistemaId,
            onSuccess = { sistema ->
                val datosCalidadAire = DatosCalidadAire(
                    id = 0L,
                    co2 = editTextCo2.text.toString().toDouble(),
                    pm25 = editTextPm25.text.toString().toDouble(),
                    pm10 = editTextPm10.text.toString().toDouble(),
                    o3 = editTextO3.text.toString().toDouble(),
                    no2 = editTextNo2.text.toString().toDouble(),
                    so2 = editTextSo2.text.toString().toDouble(),
                    fecha = Date(),
                    sistemaArduinoId = sistemaId
                )

                apiClient.crearDatosCalidadAire(
                    datosCalidadAire,
                    onSuccess = {
                        Toast.makeText(this@InsertarDatosCalidadAireActivity, "Datos enviados con éxito", Toast.LENGTH_LONG).show()
                    },
                    onFailure = { errorMessage ->
                        Toast.makeText(this@InsertarDatosCalidadAireActivity, "Error al enviar los datos: $errorMessage", Toast.LENGTH_LONG).show()
                    }
                )
            },
            onFailure = { errorMessage ->
                Toast.makeText(this@InsertarDatosCalidadAireActivity, "Error al obtener el sistema: $errorMessage", Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun validarCampos(): Boolean {
        val co2 = editTextCo2.text.toString().toDoubleOrNull()
        val pm25 = editTextPm25.text.toString().toDoubleOrNull()
        val pm10 = editTextPm10.text.toString().toDoubleOrNull()
        val o3 = editTextO3.text.toString().toDoubleOrNull()
        val no2 = editTextNo2.text.toString().toDoubleOrNull()
        val so2 = editTextSo2.text.toString().toDoubleOrNull()
        val sistemaId = editTextSistemaId.text.toString().toLongOrNull()

        if (sistemaId == null) {
            Toast.makeText(this, "Por favor, introduce un sistemaId válido", Toast.LENGTH_LONG).show()
            return false
        }

        if (co2 == null || co2 < 0.0 || co2 > 5000.0) {
            Toast.makeText(this, "Por favor, introduce un valor de CO2 válido (0 a 5000)", Toast.LENGTH_LONG).show()
            return false
        }

        if (pm25 == null || pm25 < 0.0 || pm25 > 500.0) {
            Toast.makeText(this, "Por favor, introduce un valor de PM2.5 válido (0 a 500)", Toast.LENGTH_LONG).show()
            return false
        }

        if (pm10 == null || pm10 < 0.0 || pm10 > 1000.0) {
            Toast.makeText(this, "Por favor, introduce un valor de PM10 válido (0 a 1000)", Toast.LENGTH_LONG).show()
            return false
        }

        if (o3 == null || o3 < 0.0 || o3 > 500.0) {
            Toast.makeText(this, "Por favor, introduce un valor de O3 válido (0 a 500)", Toast.LENGTH_LONG).show()
            return false
        }

        if (no2 == null || no2 < 0.0 || no2 > 250.0) {
            Toast.makeText(this, "Por favor, introduce un valor de NO2 válido (0 a 250)", Toast.LENGTH_LONG).show()
            return false
        }

        if (so2 == null || so2 < 0.0 || so2 > 1000.0) {
            Toast.makeText(this, "Por favor, introduce un valor de SO2 válido (0 a 1000)", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }
}

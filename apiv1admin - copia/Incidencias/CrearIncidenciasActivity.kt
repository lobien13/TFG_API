package com.example.apiv1admin.Incidencias

import Incidencias
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiv1admin.ApiClient
import com.example.apiv1admin.R
import java.util.Date

class CrearIncidenciasActivity : AppCompatActivity() {

        private val apiClient = ApiClient()

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.crear_incidencias_activity)

                val editTextMensaje = findViewById<EditText>(R.id.editTextMensaje)
                val editTextSistemaArduinoId = findViewById<EditText>(R.id.editTextSistemaArduinoId)

                val btnCrearIncidencia = findViewById<Button>(R.id.btnCrearIncidencia)
                btnCrearIncidencia.setOnClickListener {
                        val mensaje = editTextMensaje.text.toString()
                        val sistemaArduinoId = editTextSistemaArduinoId.text.toString().toLongOrNull()

                        // Validar que los campos no estén vacíos
                        if (mensaje.isEmpty() || sistemaArduinoId == null) {
                                mostrarMensajeError("Todos los campos son requeridos")
                                return@setOnClickListener
                        }

                        // Crear el objeto Incidencia con los datos ingresados por el usuario
                        val nuevaIncidencia = Incidencias(
                                id = null, // El ID se asigna automáticamente en el servidor
                                fecha = Date(),
                                mensaje = mensaje,
                                sistemaArduinoId = sistemaArduinoId
                        )

                        // Llamar al método para crear la nueva incidencia
                        crearNuevaIncidencia(nuevaIncidencia)
                }
        }

        private fun crearNuevaIncidencia(incidencia: Incidencias) {
                apiClient.crearIncidencia(incidencia,
                        onSuccess = { nuevaIncidencia ->
                                // Aquí puedes manejar la respuesta exitosa, como mostrar un mensaje de éxito
                                // y finalizar la actividad
                                mostrarMensaje("Incidencia creada correctamente")
                                finish()
                        },
                        onFailure = { errorMessage ->
                                // Aquí puedes manejar el error, como mostrar un mensaje de error al usuario
                                mostrarMensajeError(errorMessage)
                        }
                )
        }

        private fun mostrarMensaje(mensaje: String) {
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        private fun mostrarMensajeError(mensaje: String) {
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
}
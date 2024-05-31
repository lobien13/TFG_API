package com.example.apiv1admin.Incidencias

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiv1admin.ApiClient
import com.example.apiv1admin.R

class EliminarIncidenciasActivity : AppCompatActivity() {

    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.borrar_incidencias)

        val editTextIncidenciaId = findViewById<EditText>(R.id.editTextIncidenciaId)
        val btnEliminarIncidencia = findViewById<Button>(R.id.btnEliminarIncidencia)

        btnEliminarIncidencia.setOnClickListener {
            val incidenciaIdText = editTextIncidenciaId.text.toString()
            if (incidenciaIdText.isNotEmpty()) {
                val incidenciaId = incidenciaIdText.toLong()
                eliminarIncidencia(incidenciaId)
            } else {
                mostrarMensaje("Por favor, introduce el ID de la incidencia a eliminar")
            }
        }
    }

    private fun eliminarIncidencia(incidenciaId: Long) {
        apiClient.eliminarIncidencia(
            incidenciaId,
            onSuccess = {
                mostrarMensaje("Incidencia eliminada correctamente")
                finish() // Cerrar la actividad después de eliminar la incidencia
            },
            onFailure = { errorMessage ->
                mostrarMensajeError(errorMessage)
            }
        )
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarMensajeError(mensaje: String) {
        Toast.makeText(this, "Error: $mensaje", Toast.LENGTH_SHORT).show()
    }
}

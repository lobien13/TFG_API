package com.example.apiv1admin.Usuarios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiv1admin.ApiClient
import com.example.apiv1admin.R

class EliminarUsuarioActivity : AppCompatActivity() {

    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eliminar_usuario_activity)

        val editTextUserId = findViewById<EditText>(R.id.editTextUserId)
        val btnEliminarUsuario = findViewById<Button>(R.id.btnEliminarUsuario)

        btnEliminarUsuario.setOnClickListener {
            val userId = editTextUserId.text.toString().toLongOrNull()
            if (userId != null) {
                eliminarUsuario(userId)
            } else {
                mostrarMensajeError("ID de usuario inválido")
            }
        }
    }

    private fun eliminarUsuario(userId: Long) {
        apiClient.eliminarUsuario(userId,
            onSuccess = {
                mostrarMensaje("Usuario eliminado correctamente")
            },
            onFailure = { errorMessage ->
                mostrarMensajeError("Error al eliminar usuario: $errorMessage")
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
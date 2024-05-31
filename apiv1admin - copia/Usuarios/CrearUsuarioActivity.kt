package com.example.apiv1admin.Usuarios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apiv1admin.ApiClient
import com.example.apiv1admin.R

class CrearUsuarioActivity : AppCompatActivity() {

    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_usuario_activity)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextCorreo = findViewById<EditText>(R.id.editTextCorreo)
        val editTextContrasena = findViewById<EditText>(R.id.editTextContrasena)

        val btnCrearUsuario = findViewById<Button>(R.id.btnCrearUsuario)
        btnCrearUsuario.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val correo = editTextCorreo.text.toString()
            val contrasena = editTextContrasena.text.toString()

            // Validar que los campos no estén vacíos
            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                mostrarMensajeError("Todos los campos son requeridos")
                return@setOnClickListener
            }

            // Crear el objeto Usuario con los datos ingresados por el usuario
            val nuevoUsuario = Usuario(
                id = 0, // Esto puede ser cualquier valor ya que se generará automáticamente en el servidor
                nombre = nombre,
                email = correo,
                contrasena = contrasena,
                sistemasArduino = null // Proporcionar null para sistemasArduino
            )

            // Llamar al método para crear el nuevo usuario
            crearNuevoUsuario(nuevoUsuario)
        }
    }

    private fun crearNuevoUsuario(usuario: Usuario) {
        apiClient.crearUsuario(usuario,
            onSuccess = { nuevoUsuario ->
                // Aquí puedes manejar la respuesta exitosa, como mostrar un mensaje de éxito
                // y finalizar la actividad
                mostrarMensaje("Usuario creado correctamente")
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

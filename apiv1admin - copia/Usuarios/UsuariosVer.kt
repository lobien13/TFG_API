package com.example.apiv1admin.Usuarios


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiv1admin.ApiClient
import com.example.apiv1admin.ApiService
import com.example.apiv1admin.DatosCalidadAire.VerDatosCalidadAireActivity
import com.example.apiv1admin.Incidencias.IncidenciasActivity
import com.example.apiv1admin.R
import com.example.apiv1admin.Sistemas.SistemasActivity

class UsuariosVer : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.usuarios_ver)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = UserAdapter(listOf()) // Inicializar el adaptador con una lista vacía
        recyclerView.adapter = adapter
        // Inicializar API
        apiClient = ApiClient()
        apiService = apiClient.retrofit.create(ApiService::class.java)

        // Configurar el botón de suma para abrir un menú desplegable
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu_users, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_obtener_usuarios -> {
                        obtenerUsuarios()
                        true
                    }
                    R.id.action_crear_usuarios -> {
                        val intent = Intent(this, CrearUsuarioActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.action_eliminar_usuarios -> {
                        val intent = Intent(this, EliminarUsuarioActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

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
    }

    private fun obtenerUsuarios() {
        apiClient.obtenerTodosLosUsuarios(
            onSuccess = { usuarios ->
                mostrarUsuarios(usuarios)
            },
            onFailure = { errorMessage ->
                mostrarError(errorMessage)
            }
        )
    }

    private fun mostrarUsuarios(usuarios: List<Usuario>) {
        // Actualizar el adaptador con la lista de usuarios obtenida
        adapter.actualizarUsuarios(usuarios)
    }

    private fun mostrarError(errorMessage: String) {
        // Puedes manejar el error aquí, por ejemplo, mostrar un Toast con el mensaje de error
        Log.e("Error", errorMessage)
    }
}
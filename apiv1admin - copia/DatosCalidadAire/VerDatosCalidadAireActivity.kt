package com.example.apiv1admin.DatosCalidadAire

import DatosCalidadAire
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
import com.example.apiv1admin.Incidencias.CrearIncidenciasActivity
import com.example.apiv1admin.Incidencias.EliminarIncidenciasActivity
import com.example.apiv1admin.Incidencias.IncidenciasActivity
import com.example.apiv1admin.R
import com.example.apiv1admin.Sistemas.SistemasActivity
import com.example.apiv1admin.Usuarios.UsuariosVer


class VerDatosCalidadAireActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DatosCalidadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_activity)

        recyclerView = findViewById(R.id.recyclerViewDatosCalidadAire)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DatosCalidadAdapter(listOf()) // Inicializar el adaptador con una lista vacía
        recyclerView.adapter = adapter

        apiClient = ApiClient()
        apiService = apiClient.retrofit.create(ApiService::class.java)

        // Configurar el botón de suma para abrir un menú desplegable
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu_datos, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_obtener_datos -> {
                        obtenerDatosCalidadAire()
                        true
                    }
                    R.id.action_crear_datos -> {
                        val intent = Intent(this, InsertarDatosCalidadAireActivity::class.java)
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

    private fun obtenerDatosCalidadAire() {
        apiClient.obtenerDatosCalidadAire(
            onSuccess = { datosCalidad ->
                runOnUiThread {
                    mostrarDatosCalidadAire(datosCalidad)
                }
            },
            onFailure = { errorMessage ->
                mostrarError(errorMessage)
            }
        )
    }

    private fun mostrarDatosCalidadAire(datosCalidad: List<DatosCalidadAire>) {
        Log.d("VerDatosCalidadAireActivity", "Datos recibidos: $datosCalidad")
        adapter.actualizarDatosCalidad(datosCalidad)
    }

    private fun mostrarError(errorMessage: String) {
        // Puedes manejar el error aquí, por ejemplo, mostrar un Toast con el mensaje de error
        Log.e("Error", errorMessage)
    }
}
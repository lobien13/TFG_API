package com.example.apiv1admin.Incidencias
import Incidencias
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
import com.example.apiv1admin.Incidencias.CrearIncidenciasActivity
import com.example.apiv1admin.Incidencias.EliminarIncidenciasActivity
import com.example.apiv1admin.R
import com.example.apiv1admin.Sistemas.SistemasActivity
import com.example.apiv1admin.Usuarios.UsuariosVer
import intra.intranet2copia.Entidades.IncidenciasAdapter

class IncidenciasActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IncidenciasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.incidencias_ver)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = IncidenciasAdapter(listOf()) // Inicializar el adaptador con una lista vacía
        recyclerView.adapter = adapter

        apiClient = ApiClient()
        apiService = apiClient.retrofit.create(ApiService::class.java)

        // Configurar el botón de suma para abrir un menú desplegable
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_obtener_incidencias -> {
                        obtenerTodasLasIncidencias()
                        true
                    }
                    R.id.action_crear_incidencia -> {
                        val intent = Intent(this, CrearIncidenciasActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.action_eliminar_incidencias -> {
                        val intent = Intent(this, EliminarIncidenciasActivity::class.java)
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

    private fun obtenerTodasLasIncidencias() {
        apiClient.obtenerTodasLasIncidencias(
            onSuccess = { incidencias ->
                mostrarIncidencias(incidencias)
            },
            onFailure = { errorMessage ->
                mostrarError(errorMessage)
            }
        )
    }

    private fun mostrarIncidencias(incidencias: List<Incidencias>) {
        // Actualizar el adaptador con la lista de incidencias obtenida
        adapter.actualizarIncidencias(incidencias)
    }

    private fun mostrarError(errorMessage: String) {
        // Puedes manejar el error aquí, por ejemplo, mostrar un Toast con el mensaje de error
        Log.e("Error", errorMessage)
    }
}

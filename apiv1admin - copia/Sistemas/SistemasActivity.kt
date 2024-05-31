package com.example.apiv1admin.Sistemas

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
import com.example.apiv1admin.Usuarios.UsuariosVer
import intra.intranet2copia.Entidades.SistemaArduino
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SistemasActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SistemasAdapter
    private var sistemasArduino = listOf<SistemaArduino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_sistemas)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = SistemasAdapter(listOf(), ::onSistemaClick, ::resolverIncidencias)
        recyclerView.adapter = adapter

        apiClient = ApiClient()
        apiService = apiClient.retrofit.create(ApiService::class.java)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu_sistemas, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_ver_sistemas -> {
                        obtenerSistemas()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        val btnUsuarios = findViewById<ImageButton>(R.id.btnUsuarios)
        btnUsuarios.setOnClickListener {
            val intent = Intent(this, UsuariosVer::class.java)
            startActivity(intent)
        }

        val btnIncidencias = findViewById<ImageButton>(R.id.btnIncidencias)
        btnIncidencias.setOnClickListener {
            val intent = Intent(this, IncidenciasActivity::class.java)
            startActivity(intent)
        }

        val btnDatosCalidadAire = findViewById<ImageButton>(R.id.btnDatosCalidadAire)
        btnDatosCalidadAire.setOnClickListener {
            val intent = Intent(this, VerDatosCalidadAireActivity::class.java)
            startActivity(intent)
        }

        val btnSistemasArduino = findViewById<ImageButton>(R.id.btnSistemasArduino)
        btnSistemasArduino.setOnClickListener {
            val intent = Intent(this, SistemasActivity::class.java)
            startActivity(intent)
        }

        obtenerSistemas()
    }

    private fun obtenerSistemas() {
        apiService.obtenerSistemasArduino().enqueue(object : Callback<List<SistemaArduino>> {
            override fun onResponse(call: Call<List<SistemaArduino>>, response: Response<List<SistemaArduino>>) {
                if (response.isSuccessful) {
                    val sistemas = response.body()
                    if (sistemas != null) {
                        mostrarSistemas(sistemas)
                    } else {
                        mostrarError("Error: la respuesta es nula")
                    }
                } else {
                    mostrarError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SistemaArduino>>, t: Throwable) {
                mostrarError(t.message ?: "Error desconocido")
            }
        })
    }

    private fun mostrarSistemas(sistemas: List<SistemaArduino>) {
        adapter.actualizarSistemas(sistemas)
    }

    private fun mostrarError(errorMessage: String) {
        Log.e("Error", errorMessage)
    }

    private fun onSistemaClick(sistema: SistemaArduino) {
        // Aquí puedes manejar lo que sucede cuando se hace clic en un sistema
    }

    private fun resolverIncidencias(sistema: SistemaArduino) {
        apiService.eliminarIncidenciasTodas(sistema.id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    sistema.incidencias = mutableListOf() // Actualiza las incidencias del sistema
                    adapter.actualizarSistemas(sistemasArduino) // Actualiza el adaptador
                } else {
                    mostrarError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                mostrarError(t.message ?: "Error desconocido al resolver incidencias")
            }
        })
    }
}
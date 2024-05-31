package com.example.apiv1admin
import DatosCalidadAire
import Incidencias
import com.example.apiv1admin.Usuarios.Usuario
import intra.intranet2copia.Entidades.SistemaArduino
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    private val BASE_URL = "http://10.0.2.2:8080/"
    private val USERNAME = "admin123"
    private val PASSWORD = "admin123prueba"

    val retrofit: Retrofit

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val credentials = Credentials.basic(USERNAME, PASSWORD)
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", credentials)
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .build()
                chain.proceed(request)
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun obtenerTodosLosUsuarios(
        onSuccess: (List<Usuario>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.obtenerTodosLosUsuarios()
        call.enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun crearUsuario(
        usuario: Usuario,
        onSuccess: (Usuario) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.crearUsuario(usuario)
        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun eliminarUsuario(
        userId: Long,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.eliminarUsuario(userId)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun obtenerTodasLasIncidencias(
        onSuccess: (List<Incidencias>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.obtenerTodasLasIncidencias()
        call.enqueue(object : Callback<List<Incidencias>> {
            override fun onResponse(call: Call<List<Incidencias>>, response: Response<List<Incidencias>>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<List<Incidencias>>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun crearIncidencia(
        incidencia: Incidencias,
        onSuccess: (Incidencias) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.crearIncidencia(incidencia)
        call.enqueue(object : Callback<Incidencias> {
            override fun onResponse(call: Call<Incidencias>, response: Response<Incidencias>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<Incidencias>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun eliminarIncidencia(
        incidenciaId: Long,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.eliminarIncidencia(incidenciaId)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun obtenerDatosCalidadAire(
        onSuccess: (List<DatosCalidadAire>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.obtenerDatosCalidadAire()
        call.enqueue(object : Callback<List<DatosCalidadAire>> {
            override fun onResponse(call: Call<List<DatosCalidadAire>>, response: Response<List<DatosCalidadAire>>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<List<DatosCalidadAire>>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }

    fun crearDatosCalidadAire(
        datosCalidadAire: DatosCalidadAire,
        onSuccess: (DatosCalidadAire) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.crearDatosCalidadAire(datosCalidadAire)
        call.enqueue(object : Callback<DatosCalidadAire> {
            override fun onResponse(call: Call<DatosCalidadAire>, response: Response<DatosCalidadAire>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<DatosCalidadAire>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }


    fun obtenerSistemasArduino(
        onSuccess: (List<SistemaArduino>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.obtenerSistemasArduino()
        call.enqueue(object : Callback<List<SistemaArduino>> {
            override fun onResponse(call: Call<List<SistemaArduino>>, response: Response<List<SistemaArduino>>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<List<SistemaArduino>>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }



    fun obtenerSistemaArduinoPorId(
        sistemaId: Long,
        onSuccess: (SistemaArduino) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.obtenerSistemaArduinoPorId(sistemaId)
        call.enqueue(object : Callback<SistemaArduino> {
            override fun onResponse(call: Call<SistemaArduino>, response: Response<SistemaArduino>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else {
                    onFailure("Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<SistemaArduino>, t: Throwable) {
                onFailure("Error en la solicitud: ${t.message}")
            }
        })
    }


}

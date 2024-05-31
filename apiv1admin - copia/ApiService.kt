    package com.example.apiv1admin

    import DatosCalidadAire
    import Incidencias
    import com.example.apiv1admin.Usuarios.Usuario
    import intra.intranet2copia.Entidades.SistemaArduino
    import retrofit2.Call
    import retrofit2.http.Body
    import retrofit2.http.DELETE
    import retrofit2.http.GET
    import retrofit2.http.POST
    import retrofit2.http.Path

    interface ApiService {

        @GET("/api/usuarios/usuarios")
        fun obtenerTodosLosUsuarios(): Call<List<Usuario>>

        @POST("/api/usuarios/usuarios/añadir")
        fun crearUsuario(@Body usuario: Usuario): Call<Usuario>

        @DELETE("/api/usuarios/eliminar/{id}")
        fun eliminarUsuario(@Path("id") id: Long): Call<Void>

        @GET("/api/incidencias/incidencias")
        fun obtenerTodasLasIncidencias(): Call<List<Incidencias>>

        @POST("/api/incidencias/incidencias/añadir")
        fun crearIncidencia(@Body incidencia: Incidencias): Call<Incidencias>

        @DELETE("/api/incidencias/eliminar/{id}")
        fun eliminarIncidencia(@Path ("id") id: Long) : Call <Void>

        @GET("/api/datosCalidadAire/datosCalidadAire")
        fun obtenerDatosCalidadAire() : Call <List<DatosCalidadAire>>

        @POST("api/datosCalidadAire/datosCalidadAire/añadir")
        fun crearDatosCalidadAire(@Body datosCalidadAire: DatosCalidadAire): Call<DatosCalidadAire>


        @GET("/api/sistemasArduino/sistemasArduino")
        fun obtenerSistemasArduino(): Call<List<SistemaArduino>>

        @GET("/api/sistemasArduino/sistemasArduino/{id}")
        fun obtenerSistemaArduinoPorId(@Path("id") id: Long): Call<SistemaArduino>

        @DELETE("/api/incidencias/sistemas/{sistemaId}/incidencias")
        fun eliminarIncidenciasTodas(@Path("sistemaId") sistemaId: Long): Call<Void>


    }

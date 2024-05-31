package intra.intranet2copia.Entidades

import DatosCalidadAire
import Incidencias
import com.example.apiv1admin.Usuarios.Usuario

import com.google.gson.annotations.SerializedName

data class SistemaArduino(
    @SerializedName("id") val id: Long,
    @SerializedName("ubicacion") val ubicacion: String,
    @SerializedName("calidadDelAire") val calidadDelAire: List<DatosCalidadAire>,
    @SerializedName("incidencias") var incidencias: List<Incidencias>,
    @SerializedName("usuario") val usuario: Usuario
)
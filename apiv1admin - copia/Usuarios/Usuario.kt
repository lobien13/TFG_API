package com.example.apiv1admin.Usuarios

import com.google.gson.annotations.SerializedName
import intra.intranet2copia.Entidades.SistemaArduino

data class Usuario(
    @SerializedName("id") val id: Long,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("email") val email: String,
    @SerializedName("contrasena") val contrasena: String,
    @SerializedName("sistemasArduino") val sistemasArduino: List<SistemaArduino>?
)

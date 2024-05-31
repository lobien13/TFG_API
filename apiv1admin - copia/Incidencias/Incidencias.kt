import com.google.gson.annotations.SerializedName
import java.util.Date

data class Incidencias(
    @SerializedName("id") val id: Long?,
    @SerializedName("fecha") val fecha: Date,
    @SerializedName("mensaje") val mensaje: String,
    @SerializedName("sistema_id") val sistemaArduinoId: Long?
)
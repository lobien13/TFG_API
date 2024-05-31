import com.google.gson.annotations.SerializedName
import java.util.Date

data class DatosCalidadAire(
    @SerializedName("id") val id: Long,
    @SerializedName("co2") val co2: Double,
    @SerializedName("pm25") val pm25: Double,
    @SerializedName("pm10") val pm10: Double,
    @SerializedName("o3") val o3: Double,
    @SerializedName("no2") val no2: Double,
    @SerializedName("so2") val so2: Double,
    @SerializedName("fecha") val fecha: Date,
    @SerializedName("sistema_id") val sistemaArduinoId: Long?
)
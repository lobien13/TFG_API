package intra.intranet2copia.Entidades
import Incidencias
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiv1admin.R

class IncidenciasAdapter(private var incidencias: List<Incidencias>) : RecyclerView.Adapter<IncidenciasAdapter.IncidenciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidenciaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.incidencia_card, parent, false)
        return IncidenciaViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidenciaViewHolder, position: Int) {
        val currentIncidencia = incidencias[position]
        holder.bind(currentIncidencia)
    }

    override fun getItemCount(): Int {
        return incidencias.size
    }

    fun actualizarIncidencias(nuevasIncidencias: List<Incidencias>) {
        incidencias = nuevasIncidencias
        notifyDataSetChanged()
    }

    class IncidenciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvidIncidencia: TextView = itemView.findViewById(R.id.tvidIncidencia)
        private val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        private val tvMensaje: TextView = itemView.findViewById(R.id.tvMensaje)
        private val tvSistemaId: TextView = itemView.findViewById(R.id.tvSistemaId)

        fun bind(incidencia: Incidencias) {
            tvidIncidencia.text = "Incidencia ID: ${incidencia.id}"
            tvFecha.text = "Fecha: ${incidencia.fecha}"
            tvMensaje.text = "Mensaje: ${incidencia.mensaje}"
            tvSistemaId.text = "Sistema ID: ${incidencia.sistemaArduinoId ?: "N/A"}"
        }
    }
}


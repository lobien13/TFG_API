package com.example.apiv1admin.Sistemas

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiv1admin.R
import intra.intranet2copia.Entidades.SistemaArduino

class SistemasAdapter(
    private var sistemasArduino: List<SistemaArduino>,
    private val onClickListener: (SistemaArduino) -> Unit,
    private val onResolveClickListener: (SistemaArduino) -> Unit
) : RecyclerView.Adapter<SistemasAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.tarjetasistema)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvUbicacion: TextView = itemView.findViewById(R.id.tvUbicacion)
        val tvCalidadDelAire: TextView = itemView.findViewById(R.id.tvCalidadDelAire)
        val tvIncidencias: TextView = itemView.findViewById(R.id.tvIncidencias)
        val tvUsuario: TextView = itemView.findViewById(R.id.tvUsuario)
        val btnResolver: Button = itemView.findViewById(R.id.btnResolver)

        fun bind(sistemaArduino: SistemaArduino, onResolveClickListener: (SistemaArduino) -> Unit) {
            tvId.text = sistemaArduino.id.toString()
            tvUbicacion.text = sistemaArduino.ubicacion
            val calidadDelAireText = sistemaArduino.calidadDelAire.joinToString(separator = ", ")
            tvCalidadDelAire.text = calidadDelAireText
            val incidenciasText = sistemaArduino.incidencias.joinToString(separator = ", ")
            tvIncidencias.text = incidenciasText
            // Comprobar si el usuario es null antes de intentar acceder a su nombre
            tvUsuario.text = sistemaArduino.usuario?.nombre ?: "Usuario desconocido"

            // Cambiar el color de la tarjeta en función de si el sistema Arduino tiene incidencias o no
            if (sistemaArduino.incidencias.isNotEmpty()) {
                cardView.setCardBackgroundColor(Color.RED)
                btnResolver.visibility = View.VISIBLE
                btnResolver.setOnClickListener { onResolveClickListener(sistemaArduino) }
            } else {
                cardView.setCardBackgroundColor(Color.GREEN)
                btnResolver.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_sistema_arduino, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sistemaArduino = sistemasArduino[position]
        holder.bind(sistemaArduino, onResolveClickListener)
        holder.itemView.setOnClickListener { onClickListener(sistemaArduino) }
    }

    override fun getItemCount() = sistemasArduino.size

    fun actualizarSistemas(nuevosSistemas: List<SistemaArduino>) {
        sistemasArduino = nuevosSistemas
        notifyDataSetChanged()
    }
}
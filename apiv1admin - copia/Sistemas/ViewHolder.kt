package com.example.apiv1admin.Sistemas;
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiv1admin.R
import intra.intranet2copia.Entidades.SistemaArduino

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvId: TextView = itemView.findViewById(R.id.tvId)
    val tvUbicacion: TextView = itemView.findViewById(R.id.tvUbicacion)
    val tvCalidadDelAire: TextView = itemView.findViewById(R.id.tvCalidadDelAire)
    val tvIncidencias: TextView = itemView.findViewById(R.id.tvIncidencias)
    val tvUsuario: TextView = itemView.findViewById(R.id.tvUsuario)

    fun bind(sistemaArduino: SistemaArduino) {
        tvId.text = sistemaArduino.id.toString()
        tvUbicacion.text = sistemaArduino.ubicacion
        tvCalidadDelAire.text = sistemaArduino.calidadDelAire.toString()
        tvIncidencias.text = sistemaArduino.incidencias.toString()
        tvUsuario.text = sistemaArduino.usuario.toString()
        // Here you can set the imageView based on your logic
    }
}
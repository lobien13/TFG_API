package com.example.apiv1admin.DatosCalidadAire

import DatosCalidadAire
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiv1admin.R

class DatosCalidadAdapter(private var datosCalidadList: List<DatosCalidadAire>) :
    RecyclerView.Adapter<DatosCalidadAdapter.DatosCalidadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosCalidadViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dato_calidad, parent, false)
        return DatosCalidadViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DatosCalidadViewHolder, position: Int) {
        val currentItem = datosCalidadList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return datosCalidadList.size
    }

    fun actualizarDatosCalidad(nuevaListaDatosCalidad: List<DatosCalidadAire>) {
        datosCalidadList = nuevaListaDatosCalidad
        notifyDataSetChanged()
    }

    inner class DatosCalidadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewCo2: TextView = itemView.findViewById(R.id.chipCo2)
        private val textViewPm25: TextView = itemView.findViewById(R.id.chipPm25)
        private val textViewPm10: TextView = itemView.findViewById(R.id.chipPm10)
        private val textViewO3: TextView = itemView.findViewById(R.id.chipO3)
        private val textViewNo2: TextView = itemView.findViewById(R.id.chipNo2)
        private val textViewSo2: TextView = itemView.findViewById(R.id.chipSo2)
        private val textViewSistema: TextView = itemView.findViewById(R.id.tvSistemaId)

        fun bind(datosCalidad: DatosCalidadAire) {
            textViewCo2.text = "CO2: ${datosCalidad.co2}"
            textViewPm25.text = "PM2.5: ${datosCalidad.pm25}"
            textViewPm10.text = "PM10: ${datosCalidad.pm10}"
            textViewO3.text = "O3: ${datosCalidad.o3}"
            textViewNo2.text = "NO2: ${datosCalidad.no2}"
            textViewSo2.text = "SO2: ${datosCalidad.so2}"
            textViewSistema.text = "Sistema ID: ${datosCalidad.sistemaArduinoId}"
        }
    }
}
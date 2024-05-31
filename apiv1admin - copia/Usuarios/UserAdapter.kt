package com.example.apiv1admin.Usuarios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiv1admin.R

class UserAdapter(private var users: List<Usuario>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = users[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun actualizarUsuarios(nuevosUsuarios: List<Usuario>) {
        users = nuevosUsuarios
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvId: TextView = itemView.findViewById(R.id.tvId)
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreo)
        private val tvContrasena: TextView = itemView.findViewById(R.id.tvidContrasena)
        private val tvSistemasArduino: TextView = itemView.findViewById(R.id.tvSistemasArduino)

        fun bind(user: Usuario) {
            tvId.text = "ID: ${user.id}"
            tvNombre.text = "Nombre: ${user.nombre}"
            tvCorreo.text = "Correo: ${user.email}"
            tvContrasena.text = "Contraseña: ${user.contrasena}"
            tvSistemasArduino.text = "Sistemas Arduino: ${user.sistemasArduino?.size ?: 0}"
        }
    }
}
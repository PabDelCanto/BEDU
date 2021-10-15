package org.bedu.bedushop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterOpciones(val options : List<Opciones>) :
    RecyclerView.Adapter<RecyclerAdapterOpciones.ViewHolder>(){
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val opcion = view.findViewById<TextView>(R.id.tvOpcion)
        private val imagen = view.findViewById<ImageView>(R.id.imgOpcion)
        private val flecha = view.findViewById<ImageView>(R.id.flechita)

        fun bind(option: Opciones){
            opcion.text = option.opcion
            imagen.setImageResource(option.vector)
            flecha.setImageResource(option.flecha)
        }




    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val opciones = options[position]
        holder.bind(opciones)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_perfil, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return options.size
    }


}
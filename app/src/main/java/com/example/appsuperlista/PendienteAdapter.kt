package com.example.appsuperlista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperlista.databinding.ItemPendienteBinding
import com.example.appclasebasedatos.model.Pendiente


class PendienteAdapter(var pendientes:List<Pendiente> = emptyList()):RecyclerView.Adapter<PendienteAdapter.PendienteAdapterViewHolder>(){

    lateinit var supermercado:String
    lateinit var precio:String
    lateinit var articulo:String
    lateinit var setOnClickPendiente:(Pendiente) -> Unit
    lateinit var setOnClickListenerDelete:(Pendiente) -> Unit
    inner class PendienteAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private var binding:ItemPendienteBinding = ItemPendienteBinding.bind(itemView)
        fun bind(pendiente: Pendiente){
            binding.txtArcticulo.text = pendiente.articulo
            binding.txtCantidad.text = pendiente.cantidad
            binding.txtLimite.text = pendiente.limite
            binding.btnDetalle.setOnClickListener{
                supermercado = pendiente.supermercado
                precio = pendiente.precio
                articulo = pendiente.articulo
                setOnClickPendiente(pendiente)
            }
            binding.btnEliminar.setOnClickListener{
                setOnClickListenerDelete(pendiente)
            }

        }
    }
    fun updateListPets(pendientes:List<Pendiente>){
        this.pendientes = pendientes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendienteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pendiente, parent, false)
        return PendienteAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendienteAdapterViewHolder, position: Int) {
        val pet = pendientes[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return pendientes.size
    }

}
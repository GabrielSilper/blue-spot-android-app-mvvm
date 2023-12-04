package com.ifam.pdm.bluespotappmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifam.pdm.bluespotappmvvm.databinding.CardPropriedadeBinding
import com.ifam.pdm.bluespotappmvvm.models.entities.Property

class PropertyAdapter() : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    private var properties: MutableList<Property> = mutableListOf()

    fun setProperties(properties: List<Property>) {
        this.properties = properties.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardPropriedadeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = properties.size

    override fun onBindViewHolder(holder: PropertyAdapter.ViewHolder, position: Int) {
        val property = properties[position]
        holder.bind(property)
    }

    class ViewHolder(private val binding: CardPropriedadeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Property) {
            val valor = "Valor: R$" + property.price.toString()
            binding.descricaoEt.text = property.description
            binding.tipoEt.text = property.propertyType.value.uppercase()
            binding.valorEt.text = valor
            binding.garagemImage.visibility = if (property.hasGarage) View.VISIBLE else View.INVISIBLE
            binding.verificadoImage.visibility = if (property.isVerified) View.VISIBLE else View.INVISIBLE
        }
    }
}
package com.ifam.pdm.bluespotappmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifam.pdm.bluespotappmvvm.databinding.ItemListBinding

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder>() {

    var itemList: MutableList<String> = mutableListOf()

    inner class ItemListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.txtItem.text = item
        }
    }

    fun addItem(item: String) {
        itemList.add(item)
        notifyItemInserted(itemList.size - 1)
    }

    fun setList(items: List<String>) {
        itemList = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ItemListViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }
}
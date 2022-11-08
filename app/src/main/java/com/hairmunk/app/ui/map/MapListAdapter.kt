package com.hairmunk.app.ui.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hairmunk.app.databinding.ItemMapListBinding
import com.hairmunk.app.model.MapList

class MapListAdapter(val itemList : ArrayList<MapList>) : RecyclerView.Adapter<MapListAdapter.MapListViewHolder>() {

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    inner class MapListViewHolder(val binding: ItemMapListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MapList) {
            binding.tvMapItemName.text = item.name
            binding.tvMapItemRoad.text = item.road
            binding.tvMapItemAddress.text = item.address
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMapListBinding.inflate(layoutInflater, parent, false)
        return MapListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MapListViewHolder, position: Int) {
        holder.bind(itemList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
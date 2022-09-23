package com.hairmunk.app.ui.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hairmunk.app.R
import com.hairmunk.app.databinding.ItemAgencyBinding
import com.hairmunk.app.model.LoginAgency

class AgencyRvAdapter(context: Context, AgencyList : ArrayList<LoginAgency>) : RecyclerView.Adapter<AgencyRvAdapter.AgencyViewHolder>() {

    private lateinit var clickListener : OnItemClickListener
    var itemList : ArrayList<LoginAgency> = AgencyList

    private val context = context

    inner class AgencyViewHolder(val binding: ItemAgencyBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: LoginAgency) {
            binding.tvAgency.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgencyViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAgencyBinding.inflate(layoutInflater, parent, false)
        return AgencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgencyViewHolder, position: Int) {
        holder.bind(itemList[position])

        holder.itemView.setOnClickListener {
            clickListener.onClick(it, position)
        }

        if (itemList[position].click) {
            holder.binding.tvAgency.setTextColor(ContextCompat.getColor(context, R.color.light_red))
            holder.binding.ivAgencyClick.setImageResource(R.drawable.icon_radio_click)
        }else {
            holder.binding.tvAgency.setTextColor(ContextCompat.getColor(context, R.color.black_01))
            holder.binding.ivAgencyClick.setImageResource(R.drawable.icon_radio_unclick)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun clickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    fun setAgencyItem(items : ArrayList<LoginAgency>) {
        if (!items.isNullOrEmpty()){
            itemList = items
            notifyDataSetChanged()
        }
    }
}
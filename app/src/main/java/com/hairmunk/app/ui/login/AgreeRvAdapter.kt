package com.hairmunk.app.ui.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hairmunk.app.R
import com.hairmunk.app.databinding.ItemAgencyBinding
import com.hairmunk.app.databinding.ItemAgreeBinding
import com.hairmunk.app.model.LoginAgency
import com.hairmunk.app.model.LoginAgree

class AgreeRvAdapter(context: Context, AgreeList : ArrayList<LoginAgree>) : RecyclerView.Adapter<AgreeRvAdapter.AgreeViewHolder>() {

    private lateinit var clickListener : OnItemClickListener
    var agreeList : ArrayList<LoginAgree> = AgreeList

    private val context = context

    inner class AgreeViewHolder(val binding: ItemAgreeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: LoginAgree) {
            binding.tvDialogPermission.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgreeViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAgreeBinding.inflate(layoutInflater, parent, false)
        return AgreeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgreeViewHolder, position: Int) {
        holder.bind(agreeList[position])

        holder.itemView.setOnClickListener {
            clickListener.onClick(it, position)
        }

        if (agreeList[position].isCheck) {
            holder.binding.ivDialogAgree.setImageResource(R.drawable.icon_agree)
        } else {
            holder.binding.ivDialogAgree.setImageResource(R.drawable.icon_unagree)
        }
    }

    override fun getItemCount(): Int {
        return agreeList.size
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun clickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    fun setAgreeItem(items : ArrayList<LoginAgree>) {
        if (!items.isNullOrEmpty()){
            agreeList = items
            notifyDataSetChanged()
        }
    }
}
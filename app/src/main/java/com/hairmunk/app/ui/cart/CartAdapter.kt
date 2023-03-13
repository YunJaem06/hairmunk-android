package com.hairmunk.app.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.hairmunk.app.databinding.ItemCartSectionBinding
import com.hairmunk.app.databinding.ItemCartSectionHeaderBinding
import com.hairmunk.app.generated.callback.OnClickListener
import com.hairmunk.app.model.CartHeader
import com.hairmunk.app.model.CartItem
import com.hairmunk.app.model.CartProduct
import com.hairmunk.app.ui.common.ViewModelFactory


private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 1

class CartAdapter(private val clickListener: (CartItem) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val cartProducts = mutableListOf<CartProduct>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(ItemCartSectionHeaderBinding.inflate(inflater, parent, false))
            else -> ItemViewHolder(ItemCartSectionBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> {
                val item = cartProducts[position] as CartHeader
                holder.bind(item)
            }
            is ItemViewHolder -> {
                val item = cartProducts[position] as CartItem
                holder.bind(item, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(cartProducts[position]) {
            is CartHeader -> VIEW_TYPE_HEADER
            is CartItem -> VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    fun submitHeaderAndItemList(items : List<CartItem>) {
        val itemGroups = items.groupBy { it.brandName }
        val products = mutableListOf<CartProduct>()
        itemGroups.entries.forEach { entry ->
            val header = CartHeader(entry.key)
            products.add(header)
            products.addAll(entry.value)
        }
        cartProducts.addAll(products)
        notifyItemRangeInserted(cartProducts.size, products.size)
    }

    class HeaderViewHolder(private val binding: ItemCartSectionHeaderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(header: CartHeader) {
            binding.header = header
            binding.executePendingBindings()
        }
    }

    class ItemViewHolder(private val binding: ItemCartSectionBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem, clickListener: (CartItem) -> Unit) {
            binding.item = item
            binding.executePendingBindings()
            binding.btnDeleteCartItem.setOnClickListener {
                clickListener(item)
            }
        }
    }
}
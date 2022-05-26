package com.hairmunk.app.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hairmunk.app.databinding.ItemCategoryTopSellingBinding
import com.hairmunk.app.model.Category
import com.hairmunk.app.ui.common.CategoryDiffCallback

class CategoryTopSellingItemAdapter :
    ListAdapter<Category, CategoryTopSellingItemAdapter.TopSellingItemViewHolder>(
        CategoryDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSellingItemViewHolder {
        val binding = ItemCategoryTopSellingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopSellingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopSellingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TopSellingItemViewHolder(private val binding: ItemCategoryTopSellingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
        }
    }
}
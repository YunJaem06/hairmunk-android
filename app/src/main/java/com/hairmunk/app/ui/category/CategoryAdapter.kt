package com.hairmunk.app.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hairmunk.app.databinding.ItemCategoryBinding
import com.hairmunk.app.model.Category
import com.hairmunk.app.ui.common.CategoryDiffCallback

class CategoryAdapter(private val viewModel: CategoryViewModel): ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(
    CategoryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category: Category){
            binding.viewModel = viewModel
            binding.category = category
            binding.executePendingBindings()
        }
    }
}
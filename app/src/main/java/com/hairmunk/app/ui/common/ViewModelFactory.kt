package com.hairmunk.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hairmunk.app.AssetLoader
import com.hairmunk.app.network.ApiClient
import com.hairmunk.app.repository.category.CategoryRemoteDataSource
import com.hairmunk.app.repository.category.CategoryRepository
import com.hairmunk.app.repository.home.HomeAssetDataSource
import com.hairmunk.app.repository.home.HomeRepository
import com.hairmunk.app.ui.category.CategoryViewModel
import com.hairmunk.app.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}
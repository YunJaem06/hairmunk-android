package com.hairmunk.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hairmunk.app.AssetLoader
import com.hairmunk.app.repository.category.CategoryRemoteDataSource
import com.hairmunk.app.repository.category.CategoryRepository
import com.hairmunk.app.repository.categorydetail.CategoryDetailRemoteDataSource
import com.hairmunk.app.repository.categorydetail.CategoryDetailRepository
import com.hairmunk.app.repository.home.HomeAssetDataSource
import com.hairmunk.app.repository.home.HomeRepository
import com.hairmunk.app.repository.productdetail.ProductDetailRemoteDataSource
import com.hairmunk.app.repository.productdetail.ProductDetailRepository
import com.hairmunk.app.ServiceLocator
import com.hairmunk.app.ui.cart.CartViewModel
import com.hairmunk.app.ui.category.CategoryViewModel
import com.hairmunk.app.ui.categorydetail.CategoryDetailViewModel
import com.hairmunk.app.ui.home.HomeViewModel
import com.hairmunk.app.ui.productdetail.ProductDetailViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ServiceLocator.provideApiClient()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository = CategoryDetailRepository(CategoryDetailRemoteDataSource(
                    ServiceLocator.provideApiClient()))
                CategoryDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> {
                val repository = ProductDetailRepository(ProductDetailRemoteDataSource(ServiceLocator.provideApiClient()))
                ProductDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel() as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}
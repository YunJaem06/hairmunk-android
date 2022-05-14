package com.hairmunk.app.repository

import com.hairmunk.app.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val remoteDataSource: CategoryRemoteDataSource) {

    suspend fun getCategories(): List<Category>{
        return remoteDataSource.getCategories()
    }
}
package com.hairmunk.app.repository

import com.hairmunk.app.model.Category
import com.hairmunk.app.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient): CategoryDataSource {

    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}
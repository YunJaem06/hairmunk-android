package com.hairmunk.app.repository.categorydetail

import com.hairmunk.app.model.CategoryDetail
import com.hairmunk.app.network.ApiClient

class CategoryDetailRemoteDataSource(private val api: ApiClient): CategoryDetailDataSource {
    override suspend fun getCategoryDetail(): CategoryDetail {
        return api.getCategoryDetail("shampoo")
    }
}
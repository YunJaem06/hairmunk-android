package com.hairmunk.app.repository.categorydetail

import com.hairmunk.app.model.CategoryDetail

class CategoryDetailRepository(private val remoteDataSource: CategoryDetailRemoteDataSource) {

    suspend fun getCategoryDetail(): CategoryDetail {
        return remoteDataSource.getCategoryDetail()
    }
}
package com.hairmunk.app.repository.categorydetail

import com.hairmunk.app.model.CategoryDetail

interface CategoryDetailDataSource {

    suspend fun getCategoryDetail(): CategoryDetail
}
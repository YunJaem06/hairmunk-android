package com.hairmunk.app.repository.category

import com.hairmunk.app.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): List<Category>
}
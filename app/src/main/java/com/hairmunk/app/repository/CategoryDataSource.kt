package com.hairmunk.app.repository

import com.hairmunk.app.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): List<Category>
}
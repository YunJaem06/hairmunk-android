package com.hairmunk.app.repository.productdetail

import com.hairmunk.app.model.Product

interface ProductDetailDataSource {

    suspend fun getProductDetail(productId: String): Product
}
package com.hairmunk.app.repository.productdetail

import com.hairmunk.app.model.Product
import com.hairmunk.app.network.ApiClient

class ProductDetailRemoteDataSource(private val api: ApiClient): ProductDetailDataSource {
    override suspend fun getProductDetail(productId: String): Product {
        return api.getProductDetail(productId)
    }
}
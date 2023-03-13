package com.hairmunk.app.repository.cart

import com.hairmunk.app.model.CartItem
import com.hairmunk.app.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val localDataSource: CartItemLocalDataSource) {

    suspend fun addCartItem(product: Product) {
        withContext(Dispatchers.IO) {
            val cartItem = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            localDataSource.addCartItem(cartItem)
        }
    }

    suspend fun deleteCartItem(cartItem: CartItem) {
        withContext(Dispatchers.IO){
            localDataSource.deleteCartItem(cartItem)
        }
    }

    suspend fun getCartItems(): List<CartItem> {
        return withContext(Dispatchers.IO) {
            localDataSource.getCartItems()
        }
    }
}
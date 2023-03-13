package com.hairmunk.app.repository.cart

import com.hairmunk.app.database.CartItemDao
import com.hairmunk.app.model.CartItem

class CartItemLocalDataSource(private val dao: CartItemDao) : CartItemDataSource {

    override suspend fun addCartItem(cartItem: CartItem) {
        dao.insert(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: CartItem){
        dao.delete(cartItem)
    }

    override suspend fun getCartItems(): List<CartItem> {
        return dao.load()
    }
}
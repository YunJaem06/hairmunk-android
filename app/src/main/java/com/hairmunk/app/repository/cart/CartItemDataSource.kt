package com.hairmunk.app.repository.cart

import com.hairmunk.app.model.CartItem

interface CartItemDataSource {

    suspend fun addCartItem(cartItem: CartItem)

    suspend fun deleteCartItem(cartItem: CartItem)

    suspend fun getCartItems(): List<CartItem>
}
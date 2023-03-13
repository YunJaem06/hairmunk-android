package com.hairmunk.app.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hairmunk.app.model.CartItem
import com.hairmunk.app.model.Product
import com.hairmunk.app.repository.cart.CartRepository
import com.hairmunk.app.ui.common.Event
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository): ViewModel() {

    private val _items = MutableLiveData<List<CartItem>>()
    val items: LiveData<List<CartItem>> = _items

    private val _deleteCartEvent = MutableLiveData<Event<Unit>>()
    val deleteCartEvent: LiveData<Event<Unit>> = _deleteCartEvent

    init {
        loadCartItem()
    }

    private fun loadCartItem() {
        viewModelScope.launch {
            _items.value = cartRepository.getCartItems()
        }
    }

    fun deleteCart(cartItem: CartItem){
        viewModelScope.launch {
            cartRepository.deleteCartItem(cartItem)
            _deleteCartEvent.value = Event(Unit)
        }
    }
}
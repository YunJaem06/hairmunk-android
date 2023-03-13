package com.hairmunk.app.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hairmunk.app.model.Product
import com.hairmunk.app.repository.cart.CartRepository
import com.hairmunk.app.repository.productdetail.ProductDetailRepository
import com.hairmunk.app.ui.common.Event
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productDetailRepository: ProductDetailRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _addCartEvent = MutableLiveData<Event<Unit>>()
    val addCartEvent: LiveData<Event<Unit>> = _addCartEvent


    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            _product.value = productDetailRepository.getProductDetail(productId)
        }
    }

    fun addCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addCartItem(product)
            _addCartEvent.value = Event(Unit)
        }
    }

}
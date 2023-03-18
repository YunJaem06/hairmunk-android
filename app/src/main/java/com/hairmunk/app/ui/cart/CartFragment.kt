package com.hairmunk.app.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hairmunk.app.BaseFragment
import com.hairmunk.app.R
import com.hairmunk.app.databinding.FragmentCartBinding
import com.hairmunk.app.model.CartItem
import com.hairmunk.app.ui.common.EventObserver
import com.hairmunk.app.ui.common.ViewModelFactory
import com.hairmunk.app.ui.productdetail.ProductDetailViewModel

class CartFragment: BaseFragment<FragmentCartBinding>(R.layout.fragment_cart) {

    private val viewModel: CartViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun init() {
        setListAdapter()
        deleteCart()
    }

    private fun deleteCart(){
        viewModel.deleteCartEvent.observe(viewLifecycleOwner, EventObserver{
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("삭제했습니다.")
                .setPositiveButton("확인") { dialog, which ->
                }
                .show()
        })
    }

    private fun setListAdapter() {
        val cartAdapter = CartAdapter { selectedItem : CartItem -> listItemClicked(selectedItem) }
        binding.rvCartItem.adapter = cartAdapter
        viewModel.items.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.submitHeaderAndItemList(cartItems)
        }
    }

    private fun listItemClicked(cartItem: CartItem){
        viewModel.deleteCart(cartItem)
    }
}
package com.delivery.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.app.data.model.CartItem
import com.delivery.app.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    private val _itemCount = MutableStateFlow(0)
    val itemCount: StateFlow<Int> = _itemCount

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            cartRepository.getAllCartItems().collect { items ->
                _cartItems.value = items
                calculateTotals(items)
            }
        }
    }

    private fun calculateTotals(items: List<CartItem>) {
        _totalPrice.value = items.sumOf { it.price * it.quantity }
        _itemCount.value = items.sumOf { it.quantity }
    }

    fun addToCart(item: CartItem) {
        viewModelScope.launch {
            cartRepository.addToCart(item)
        }
    }

    fun updateQuantity(item: CartItem, quantity: Int) {
        viewModelScope.launch {
            val updatedItem = item.copy(quantity = quantity)
            cartRepository.updateCartItem(updatedItem)
        }
    }

    fun removeFromCart(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeFromCart(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }
}
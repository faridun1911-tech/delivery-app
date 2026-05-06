package com.delivery.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.app.data.model.Order
import com.delivery.app.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _userOrders = MutableStateFlow<List<Order>>(emptyList())
    val userOrders: StateFlow<List<Order>> = _userOrders

    private val _currentOrder = MutableStateFlow<Order?>(null)
    val currentOrder: StateFlow<Order?> = _currentOrder

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadUserOrders(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                orderRepository.getUserOrders(userId).collect { orders ->
                    _userOrders.value = orders
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createOrder(order: Order) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                orderRepository.createOrder(order).collect { createdOrder ->
                    _currentOrder.value = createdOrder
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun trackOrder(orderId: String) {
        viewModelScope.launch {
            try {
                orderRepository.getOrderById(orderId).collect { order ->
                    _currentOrder.value = order
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

    fun cancelOrder(orderId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                orderRepository.cancelOrder(orderId).collect { cancelledOrder ->
                    _currentOrder.value = cancelledOrder
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun rateOrder(orderId: String, rating: Int, review: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                orderRepository.rateOrder(orderId, rating, review).collect { ratedOrder ->
                    _currentOrder.value = ratedOrder
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
package com.delivery.app.domain.repository

import com.delivery.app.data.api.ApiClient
import com.delivery.app.data.api.DeliveryApiService
import com.delivery.app.data.api.OrderRatingRequest
import com.delivery.app.data.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val apiService: DeliveryApiService = ApiClient.getApiService()
) {
    fun createOrder(order: Order): Flow<Order> = flow {
        try {
            val createdOrder = apiService.createOrder(order)
            emit(createdOrder)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getOrderById(orderId: String): Flow<Order> = flow {
        try {
            val order = apiService.getOrderById(orderId)
            emit(order)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getUserOrders(userId: String): Flow<List<Order>> = flow {
        try {
            val orders = apiService.getUserOrders(userId)
            emit(orders)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun cancelOrder(orderId: String): Flow<Order> = flow {
        try {
            val cancelledOrder = apiService.cancelOrder(orderId)
            emit(cancelledOrder)
        } catch (e: Exception) {
            throw e
        }
    }

    fun rateOrder(orderId: String, rating: Int, review: String): Flow<Order> = flow {
        try {
            val ratedOrder = apiService.rateOrder(
                orderId,
                OrderRatingRequest(rating, review)
            )
            emit(ratedOrder)
        } catch (e: Exception) {
            throw e
        }
    }
}
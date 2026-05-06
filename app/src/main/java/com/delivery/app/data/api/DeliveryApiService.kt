package com.delivery.app.data.api

import com.delivery.app.data.model.MenuItem
import com.delivery.app.data.model.Order
import com.delivery.app.data.model.Store
import com.delivery.app.data.model.User
import retrofit2.http.*

interface DeliveryApiService {
    // Store endpoints
    @GET("/api/stores")
    suspend fun getAllStores(): List<Store>

    @GET("/api/stores/{storeId}")
    suspend fun getStoreById(@Path("storeId") storeId: String): Store

    @GET("/api/stores/category/{category}")
    suspend fun getStoresByCategory(@Path("category") category: String): List<Store>

    @POST("/api/stores/search")
    suspend fun searchStores(@Query("q") query: String): List<Store>

    // Menu Item endpoints
    @GET("/api/menu/{storeId}")
    suspend fun getMenuItems(@Path("storeId") storeId: String): List<MenuItem>

    @GET("/api/menu/item/{itemId}")
    suspend fun getMenuItemById(@Path("itemId") itemId: String): MenuItem

    @GET("/api/menu/{storeId}/category/{category}")
    suspend fun getMenuItemsByCategory(
        @Path("storeId") storeId: String,
        @Path("category") category: String
    ): List<MenuItem>

    @POST("/api/menu/search")
    suspend fun searchMenuItems(@Query("q") query: String): List<MenuItem>

    // Order endpoints
    @POST("/api/orders")
    suspend fun createOrder(@Body order: Order): Order

    @GET("/api/orders/{orderId}")
    suspend fun getOrderById(@Path("orderId") orderId: String): Order

    @GET("/api/orders/user/{userId}")
    suspend fun getUserOrders(@Path("userId") userId: String): List<Order>

    @PUT("/api/orders/{orderId}")
    suspend fun updateOrder(
        @Path("orderId") orderId: String,
        @Body order: Order
    ): Order

    @POST("/api/orders/{orderId}/cancel")
    suspend fun cancelOrder(@Path("orderId") orderId: String): Order

    @POST("/api/orders/{orderId}/rate")
    suspend fun rateOrder(
        @Path("orderId") orderId: String,
        @Body rating: OrderRatingRequest
    ): Order

    // User endpoints
    @POST("/api/auth/register")
    suspend fun registerUser(@Body user: User): AuthResponse

    @POST("/api/auth/login")
    suspend fun loginUser(@Body credentials: LoginRequest): AuthResponse

    @GET("/api/users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): User

    @PUT("/api/users/{userId}")
    suspend fun updateUserProfile(
        @Path("userId") userId: String,
        @Body user: User
    ): User
}

data class AuthResponse(
    val token: String,
    val user: User
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class OrderRatingRequest(
    val rating: Int,
    val review: String
)
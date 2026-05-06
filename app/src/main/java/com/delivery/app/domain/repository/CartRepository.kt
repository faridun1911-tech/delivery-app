package com.delivery.app.domain.repository

import com.delivery.app.data.dao.CartDao
import com.delivery.app.data.model.CartItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    fun getAllCartItems(): Flow<List<CartItem>> = cartDao.getAllCartItems()

    fun getCartItemsByStore(storeId: String): Flow<List<CartItem>> =
        cartDao.getCartItemsByStore(storeId)

    suspend fun addToCart(item: CartItem) = cartDao.insert(item)

    suspend fun updateCartItem(item: CartItem) = cartDao.update(item)

    suspend fun removeFromCart(item: CartItem) = cartDao.delete(item)

    suspend fun clearCart() = cartDao.clearCart()

    suspend fun clearStoreCart(storeId: String) = cartDao.clearStoreCart(storeId)

    fun getCartItemCount(): Flow<Int> = cartDao.getCartItemCount()
}
package com.delivery.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.delivery.app.data.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Query("SELECT * FROM cart_items WHERE storeId = :storeId")
    fun getCartItemsByStore(storeId: String): Flow<List<CartItem>>

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("DELETE FROM cart_items WHERE storeId = :storeId")
    suspend fun clearStoreCart(storeId: String)

    @Query("SELECT COUNT(*) FROM cart_items")
    fun getCartItemCount(): Flow<Int>
}
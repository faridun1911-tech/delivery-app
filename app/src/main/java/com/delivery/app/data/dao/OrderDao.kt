package com.delivery.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.delivery.app.data.model.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insert(order: Order)

    @Update
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)

    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrderById(orderId: String): Flow<Order>

    @Query("SELECT * FROM orders WHERE userId = :userId ORDER BY createdAt DESC")
    fun getUserOrders(userId: String): Flow<List<Order>>

    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun getAllOrders(): Flow<List<Order>>

    @Query("DELETE FROM orders")
    suspend fun deleteAll()
}
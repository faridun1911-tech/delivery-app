package com.delivery.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.delivery.app.data.model.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Insert
    suspend fun insert(store: Store)

    @Insert
    suspend fun insertAll(stores: List<Store>)

    @Update
    suspend fun update(store: Store)

    @Delete
    suspend fun delete(store: Store)

    @Query("SELECT * FROM stores")
    fun getAllStores(): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE id = :storeId")
    fun getStoreById(storeId: String): Flow<Store>

    @Query("SELECT * FROM stores WHERE category = :category")
    fun getStoresByCategory(category: String): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE isOpen = 1")
    fun getOpenStores(): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE name LIKE '%' || :query || '%' OR address LIKE '%' || :query || '%'")
    fun searchStores(query: String): Flow<List<Store>>

    @Query("SELECT * FROM stores ORDER BY rating DESC LIMIT :limit")
    fun getTopRatedStores(limit: Int = 10): Flow<List<Store>>

    @Query("DELETE FROM stores")
    suspend fun deleteAll()
}
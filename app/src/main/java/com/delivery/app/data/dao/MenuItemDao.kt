package com.delivery.app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.delivery.app.data.model.MenuItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Insert
    suspend fun insert(menuItem: MenuItem)

    @Insert
    suspend fun insertAll(items: List<MenuItem>)

    @Update
    suspend fun update(menuItem: MenuItem)

    @Delete
    suspend fun delete(menuItem: MenuItem)

    @Query("SELECT * FROM menu_items WHERE storeId = :storeId")
    fun getMenuItemsByStore(storeId: String): Flow<List<MenuItem>>

    @Query("SELECT * FROM menu_items WHERE id = :itemId")
    fun getMenuItemById(itemId: String): Flow<MenuItem>

    @Query("SELECT * FROM menu_items WHERE storeId = :storeId AND category = :category")
    fun getMenuItemsByCategory(storeId: String, category: String): Flow<List<MenuItem>>

    @Query("SELECT * FROM menu_items WHERE storeId = :storeId AND isAvailable = 1")
    fun getAvailableItems(storeId: String): Flow<List<MenuItem>>

    @Query("SELECT * FROM menu_items WHERE name LIKE '%' || :query || '%'")
    fun searchMenuItems(query: String): Flow<List<MenuItem>>

    @Query("SELECT * FROM menu_items WHERE storeId = :storeId ORDER BY rating DESC LIMIT :limit")
    fun getPopularItems(storeId: String, limit: Int = 10): Flow<List<MenuItem>>

    @Query("DELETE FROM menu_items")
    suspend fun deleteAll()
}
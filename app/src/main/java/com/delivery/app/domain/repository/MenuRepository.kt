package com.delivery.app.domain.repository

import com.delivery.app.data.api.DeliveryApiService
import com.delivery.app.data.dao.MenuItemDao
import com.delivery.app.data.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val apiService: DeliveryApiService,
    private val menuItemDao: MenuItemDao
) {
    fun getMenuItems(storeId: String): Flow<List<MenuItem>> = flow {
        try {
            val items = apiService.getMenuItems(storeId)
            menuItemDao.insertAll(items)
            emit(items)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getMenuItemById(itemId: String): Flow<MenuItem> = flow {
        try {
            val item = apiService.getMenuItemById(itemId)
            menuItemDao.insert(item)
            emit(item)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getMenuItemsByCategory(storeId: String, category: String): Flow<List<MenuItem>> = flow {
        try {
            val items = apiService.getMenuItemsByCategory(storeId, category)
            emit(items)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun searchMenuItems(query: String): Flow<List<MenuItem>> = flow {
        try {
            val results = apiService.searchMenuItems(query)
            emit(results)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getPopularItems(storeId: String): Flow<List<MenuItem>> = flow {
        try {
            val items = apiService.getMenuItems(storeId).sortedByDescending { it.rating }.take(10)
            emit(items)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
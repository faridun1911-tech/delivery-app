package com.delivery.app.domain.repository

import com.delivery.app.data.api.DeliveryApiService
import com.delivery.app.data.dao.StoreDao
import com.delivery.app.data.model.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val apiService: DeliveryApiService,
    private val storeDao: StoreDao
) {
    fun getAllStores(): Flow<List<Store>> = flow {
        try {
            val stores = apiService.getAllStores()
            storeDao.insertAll(stores)
            emit(stores)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getStoreById(storeId: String): Flow<Store> = flow {
        try {
            val store = apiService.getStoreById(storeId)
            storeDao.insert(store)
            emit(store)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getStoresByCategory(category: String): Flow<List<Store>> = flow {
        try {
            val stores = apiService.getStoresByCategory(category)
            emit(stores)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun searchStores(query: String): Flow<List<Store>> = flow {
        try {
            val results = apiService.searchStores(query)
            emit(results)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getTopRatedStores(): Flow<List<Store>> = flow {
        try {
            val stores = apiService.getAllStores().sortedByDescending { it.rating }.take(10)
            emit(stores)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
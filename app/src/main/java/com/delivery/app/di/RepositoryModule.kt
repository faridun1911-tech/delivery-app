package com.delivery.app.di

import com.delivery.app.data.api.ApiClient
import com.delivery.app.data.database.AppDatabase
import com.delivery.app.domain.repository.CartRepository
import com.delivery.app.domain.repository.MenuRepository
import com.delivery.app.domain.repository.OrderRepository
import com.delivery.app.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideStoreRepository(database: AppDatabase): StoreRepository {
        return StoreRepository(
            apiService = ApiClient.getApiService(),
            storeDao = database.storeDao()
        )
    }

    @Singleton
    @Provides
    fun provideMenuRepository(database: AppDatabase): MenuRepository {
        return MenuRepository(
            apiService = ApiClient.getApiService(),
            menuItemDao = database.menuItemDao()
        )
    }

    @Singleton
    @Provides
    fun provideCartRepository(database: AppDatabase): CartRepository {
        return CartRepository(cartDao = database.cartDao())
    }

    @Singleton
    @Provides
    fun provideOrderRepository(): OrderRepository {
        return OrderRepository(apiService = ApiClient.getApiService())
    }
}
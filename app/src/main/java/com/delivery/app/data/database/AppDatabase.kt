package com.delivery.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delivery.app.data.model.CartItem
import com.delivery.app.data.model.MenuItem
import com.delivery.app.data.model.Order
import com.delivery.app.data.model.Store
import com.delivery.app.data.model.User
import com.delivery.app.data.dao.*

@Database(
    entities = [
        Store::class,
        MenuItem::class,
        CartItem::class,
        User::class,
        Order::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun cartDao(): CartDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "delivery_app_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
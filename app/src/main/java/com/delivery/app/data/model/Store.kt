package com.delivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "stores")
data class Store(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: String, // StoreCategory enum
    val imageUrl: String,
    val rating: Double,
    val reviewCount: Int,
    val deliveryTime: Int, // в минутах
    val deliveryFee: Double,
    val minOrderAmount: Double,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val isOpen: Boolean,
    val tags: List<String> = emptyList(),
    val description: String = ""
) : Serializable

object StoreCategory {
    const val GROCERY = "GROCERY"           // Продуктовый магазин
    const val PHARMACY = "PHARMACY"         // Аптека
    const val FLOWERS = "FLOWERS"           // Магазин цветов
    const val RESTAURANT = "RESTAURANT"     // Ресторан
    const val CAFE = "CAFE"                 // Кафе
    const val BAKERY = "BAKERY"             // Булочная
    const val ALCOHOL = "ALCOHOL"           // Алкогольные напитки
    const val ELECTRONICS = "ELECTRONICS"   // Электроника

    fun getAll() = listOf(GROCERY, PHARMACY, FLOWERS, RESTAURANT, CAFE, BAKERY, ALCOHOL, ELECTRONICS)
    fun getDisplayName(category: String): String = when (category) {
        GROCERY -> "🛒 Продукты"
        PHARMACY -> "💊 Аптека"
        FLOWERS -> "🌹 Цветы"
        RESTAURANT -> "🍽️ Ресторан"
        CAFE -> "☕ Кафе"
        BAKERY -> "🥐 Булочная"
        ALCOHOL -> "🍾 Алкоголь"
        ELECTRONICS -> "📱 Электроника"
        else -> category
    }
}
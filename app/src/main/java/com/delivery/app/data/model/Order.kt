package com.delivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey
    val id: String,
    val userId: String,
    val storeId: String,
    val storeName: String,
    val items: List<String> = emptyList(), // JSON string
    val totalPrice: Double,
    val deliveryFee: Double,
    val subtotal: Double,
    val status: String, // OrderStatus enum
    val deliveryAddress: String,
    val recipientName: String,
    val recipientPhone: String,
    val notes: String = "",
    val estimatedDeliveryTime: Int, // в минутах
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null,
    val rating: Int? = null,
    val review: String? = null,
    val courierName: String? = null,
    val courierPhone: String? = null
)

data class OrderItem(
    val menuItemId: String,
    val itemName: String,
    val price: Double,
    val quantity: Int,
    val notes: String = ""
)

object OrderStatus {
    const val PENDING = "PENDING"                   // Ожидание подтверждения
    const val CONFIRMED = "CONFIRMED"              // Подтверждено
    const val PREPARING = "PREPARING"              // Готовится
    const val READY_FOR_PICKUP = "READY_FOR_PICKUP" // Готово для доставки
    const val ON_THE_WAY = "ON_THE_WAY"            // В пути
    const val DELIVERED = "DELIVERED"              // Доставлено
    const val CANCELLED = "CANCELLED"              // Отменено

    fun getDisplayName(status: String): String = when (status) {
        PENDING -> "⏳ Ожидание подтверждения"
        CONFIRMED -> "✅ Подтверждено"
        PREPARING -> "👨‍🍳 Готовится"
        READY_FOR_PICKUP -> "📦 Готово к доставке"
        ON_THE_WAY -> "🚴 В пути"
        DELIVERED -> "✔️ Доставлено"
        CANCELLED -> "❌ Отменено"
        else -> status
    }
}
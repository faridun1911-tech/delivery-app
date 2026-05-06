package com.delivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val menuItemId: String,
    val storeId: String,
    val itemName: String,
    val price: Double,
    val quantity: Int,
    val notes: String = "",
    val imageUrl: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

data class Cart(
    val items: List<CartItem>,
    val storeId: String,
    val storeName: String
) {
    fun getTotalPrice(): Double = items.sumOf { it.price * it.quantity }
    fun getItemCount(): Int = items.sumOf { it.quantity }
}
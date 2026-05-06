package com.delivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "menu_items")
data class MenuItem(
    @PrimaryKey
    val id: String,
    val storeId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val isAvailable: Boolean = true,
    val preparationTime: Int = 0, // в минутах
    val calories: Int? = null,
    val tags: List<String> = emptyList() // Вегетарианское, острое и т.д.
) : Serializable
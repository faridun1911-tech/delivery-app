package com.delivery.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val email: String,
    val phone: String,
    val firstName: String,
    val lastName: String,
    val profileImageUrl: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

data class Address(
    val id: String,
    val title: String, // Дом, Офис и т.д.
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val isDefault: Boolean = false
)

data class PaymentMethod(
    val id: String,
    val type: String, // PaymentType
    val cardNumber: String? = null,
    val cardholderName: String? = null,
    val expiryDate: String? = null,
    val isDefault: Boolean = false,
    val walletBalance: Double = 0.0
)

object PaymentType {
    const val CARD = "CARD"
    const val WALLET = "WALLET"
    const val CASH_ON_DELIVERY = "CASH_ON_DELIVERY"
}
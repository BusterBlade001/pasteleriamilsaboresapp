package com.pasteleria1000sabores.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderNumber: String,
    val totalAmount: Int,
    val discount: Int = 0,
    val finalAmount: Int,
    val status: String = "pending", // pending, confirmed, preparing, in_transit, delivered, cancelled
    val deliveryDate: String?,
    val deliveryAddress: String?,
    val notes: String?,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "order_items")
data class OrderItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderId: Int,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val unitPrice: Int,
    val subtotal: Int,
    val customMessage: String?
)

package com.pasteleria1000sabores.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,
    val code: String,
    val categoryId: Int,
    val name: String,
    val description: String?,
    val price: Int,
    val imageUrl: String?,
    val shape: String, // cuadrada, circular, individual, otro
    val size: String?,
    val customizable: Boolean = false,
    val available: Boolean = true
)

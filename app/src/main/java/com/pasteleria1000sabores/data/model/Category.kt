package com.pasteleria1000sabores.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey
    val id: Int,
    val code: String,
    val name: String,
    val description: String?
)

package com.pasteleria1000sabores.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val email: String?,
    val birthDate: String?,
    val age: Int?,
    val hasLifetimeDiscount: Boolean = false,
    val discountCode: String?,
    val isDuocStudent: Boolean = false
)

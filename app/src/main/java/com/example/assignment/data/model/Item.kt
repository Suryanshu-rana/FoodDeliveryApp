package com.example.assignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
    val id: Long,
    var name: String,
    var icon: String,
    var price: Double,
    var quantity: Long = 0,
    var favourite: Boolean = false
)

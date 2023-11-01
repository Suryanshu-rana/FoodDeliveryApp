package com.example.assignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    var name: String,
    var items: List<Item>
)
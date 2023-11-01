package com.example.assignment.data.model.converter

import androidx.room.TypeConverter
import com.example.assignment.data.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromList(items: List<Item>): String {
        return Gson().toJson(items)
    }

    @TypeConverter
    fun toList(list: String): List<Item>? {
        val listType = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(list, listType)
    }
}
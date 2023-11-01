package com.example.assignment.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.Product
import com.example.assignment.data.model.ProductDao
import com.example.assignment.data.model.converter.Converter

@Database(entities = [Item::class, Product::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "MyDatabase"
                ).build()
            }
        }
    }
}
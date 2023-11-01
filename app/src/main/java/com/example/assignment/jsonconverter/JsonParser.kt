package com.example.assignment.jsonconverter

import android.content.Context
import android.util.Log
import com.example.assignment.data.model.Categories
import com.example.assignment.data.model.Product
import com.google.gson.Gson

class JsonParser {
    /**
     * @author Suryanshu
     * This is a method to Open the json file and extract all the data from it.
     * @param context Context to create input stream to open json file.
     * @return response that contains json data.
     * */
    fun getQuotesFromJson(context: Context): List<Product> {
        // Your Json file should be contain as an array that it will parse otherwise it will give error
        val inputStream = context.assets.open("categories.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val response = gson.fromJson(json, Categories::class.java)
        Log.v("List of categories", response.toString())
        return response.categories
    }

}
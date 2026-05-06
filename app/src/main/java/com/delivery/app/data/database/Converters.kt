package com.delivery.app.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        return if (value == null) emptyList()
        else gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String {
        return gson.toJson(list ?: emptyList<String>())
    }
}
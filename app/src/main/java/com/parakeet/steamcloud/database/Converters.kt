package com.parakeet.steamcloud.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Room database type converters
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun stringListToString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun fromStringMap(value: String?): Map<String, String>? {
        if (value == null) return null
        return value.split("|").associate { entry ->
            val parts = entry.split(":")
            parts[0] to if (parts.size > 1) parts[1] else ""
        }
    }

    @TypeConverter
    fun stringMapToString(map: Map<String, String>?): String? {
        return map?.map { (k, v) -> "$k:$v" }?.joinToString("|")
    }
}
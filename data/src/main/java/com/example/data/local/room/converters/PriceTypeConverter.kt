package com.example.data.local.room.converters

import androidx.room.TypeConverter
import com.example.data.model.PriceDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PriceTypeConverter {
    @TypeConverter
    fun fromSource(price: PriceDto): String {
        return Json.encodeToString(price)
    }

    @TypeConverter
    fun toSource(price: String): PriceDto {
        return Json.decodeFromString(price)
    }
}




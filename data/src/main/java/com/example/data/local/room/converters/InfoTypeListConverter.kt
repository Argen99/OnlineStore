package com.example.data.local.room.converters

import androidx.room.TypeConverter
import com.example.data.model.InfoDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class InfoTypeListConverter {
    @TypeConverter
    fun fromObj(info: List<InfoDto>): String {
        return Json.encodeToString(info)
    }

    @TypeConverter
    fun toObj(info: String): List<InfoDto> {
        return Json.decodeFromString(info)
    }
}
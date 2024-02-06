package com.example.data.local.room.converters

import androidx.room.TypeConverter
import com.example.data.model.FeedbackDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


class FeedbackTypeConverter {
    @TypeConverter
    fun fromObj(feedback: FeedbackDto): String {
        return Json.encodeToString(feedback)
    }

    @TypeConverter
    fun toObj(feedback: String): FeedbackDto {
        return Json.decodeFromString(feedback)
    }
}
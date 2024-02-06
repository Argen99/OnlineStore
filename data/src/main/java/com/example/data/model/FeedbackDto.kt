package com.example.data.model

import com.example.data.core.DataMapper
import com.example.domain.model.FeedbackModel
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackDto(
    val count: Int,
    val rating: Double
) : DataMapper<FeedbackModel> {
    override fun toDomain() = FeedbackModel(
        count = count,
        rating = rating
    )
}

fun FeedbackModel.toDto() = FeedbackDto(
    count = count,
    rating = rating
)
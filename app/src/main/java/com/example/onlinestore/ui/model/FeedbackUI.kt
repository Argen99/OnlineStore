package com.example.onlinestore.ui.model

import com.example.data.core.DataMapper
import com.example.domain.model.FeedbackModel
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackUI(
    val count: Int,
    val rating: Double
) : DataMapper<FeedbackModel>, java.io.Serializable {

    override fun toDomain() = FeedbackModel(
        count = count,
        rating = rating
    )
}

fun FeedbackModel.toUI() = FeedbackUI(
    count = count,
    rating = rating
)
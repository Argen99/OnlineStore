package com.example.data.model

import com.example.data.core.DataMapper
import com.example.domain.model.InfoModel
import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val title: String,
    val value: String
) : DataMapper<InfoModel> {
    override fun toDomain() = InfoModel(
        title = title,
        value = value
    )
}

fun InfoModel.toDto() = InfoDto(
    title = title,
    value = value
)
package com.example.onlinestore.ui.model

import com.example.data.core.DataMapper
import com.example.domain.model.InfoModel
import kotlinx.serialization.Serializable

@Serializable
data class InfoUI(
    val title: String,
    val value: String
) : DataMapper<InfoModel>, java.io.Serializable {

    override fun toDomain() = InfoModel(
        title = title,
        value = value
    )
}

fun InfoModel.toUI() = InfoUI(
    title = title,
    value = value
)
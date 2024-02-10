package com.example.onlinestore.ui.model

import com.example.data.core.DataMapper
import com.example.domain.model.ProductModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductUI(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: PriceUI,
    val feedback: FeedbackUI?,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<InfoUI>,
    val ingredients: String,
    var isFavorite: Boolean = false,
    var images: List<Int> = emptyList()
) : DataMapper<ProductModel>, java.io.Serializable {

    override fun toDomain() = ProductModel(
        id = id,
        title = title,
        subtitle = subtitle,
        price = price.toDomain(),
        feedback = feedback?.toDomain(),
        tags = tags,
        available = available,
        description = description,
        info = info.map { it.toDomain() },
        ingredients = ingredients
    )
}

fun ProductModel.toUI() = ProductUI(
    id = id,
    title = title,
    subtitle = subtitle,
    price = price.toDto(),
    feedback = feedback?.toUI(),
    tags = tags,
    available = available,
    description = description,
    info = info.map { it.toUI() },
    ingredients = ingredients
)
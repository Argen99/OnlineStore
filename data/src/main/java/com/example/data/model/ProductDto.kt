package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.core.DataMapper
import com.example.domain.model.ProductModel

@Entity(tableName = "products")
data class ProductDto(
    @PrimaryKey
    val id: String,
    val title: String,
    val subtitle: String,
    val price: PriceDto,
    val feedback: FeedbackDto?,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<InfoDto>,
    val ingredients: String
) : DataMapper<ProductModel> {
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

fun ProductModel.toDto() = ProductDto(
    id = id,
    title = title,
    subtitle = subtitle,
    price = price.toDto(),
    feedback = feedback?.toDto(),
    tags = tags,
    available = available,
    description = description,
    info = info.map { it.toDto() },
    ingredients = ingredients
)
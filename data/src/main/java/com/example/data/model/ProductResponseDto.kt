package com.example.data.model

import com.example.data.core.DataMapper
import com.example.domain.model.ProductResponseModel

data class ProductResponseDto(
    val items: List<ProductDto>
) : DataMapper<ProductResponseModel> {
    override fun toDomain() = ProductResponseModel(
        items = items.map { it.toDomain() }
    )
}
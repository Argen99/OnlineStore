package com.example.data.model

import com.example.data.core.DataMapper
import com.example.domain.model.PriceModel
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
) : DataMapper<PriceModel> {
    override fun toDomain() = PriceModel(
        price = price,
        discount = discount,
        priceWithDiscount = priceWithDiscount,
        unit = unit
    )
}

fun PriceModel.toDto() = PriceDto(
    price = price,
    discount = discount,
    priceWithDiscount = priceWithDiscount,
    unit = unit
)
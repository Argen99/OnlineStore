package com.example.onlinestore.ui.model

import com.example.data.core.DataMapper
import com.example.domain.model.PriceModel
import com.example.domain.model.ProductModel
import kotlinx.serialization.Serializable

@Serializable
data class PriceUI(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
) : DataMapper<PriceModel>, java.io.Serializable {

    override fun toDomain() = PriceModel(
        price = price,
        discount = discount,
        priceWithDiscount = priceWithDiscount,
        unit = unit
    )
}

fun PriceModel.toDto() = PriceUI(
    price = price,
    discount = discount,
    priceWithDiscount = priceWithDiscount,
    unit = unit
)
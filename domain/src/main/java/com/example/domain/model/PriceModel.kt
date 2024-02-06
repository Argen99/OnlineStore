package com.example.domain.model

data class PriceModel(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)
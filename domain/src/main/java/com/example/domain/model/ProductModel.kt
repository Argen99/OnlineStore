package com.example.domain.model

data class ProductModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: PriceModel,
    val feedback: FeedbackModel?,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val info: List<InfoModel>,
    val ingredients: String
)
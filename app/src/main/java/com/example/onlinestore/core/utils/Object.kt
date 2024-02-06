package com.example.onlinestore.core.utils

import com.example.onlinestore.R
import com.example.onlinestore.ui.model.CategoryUI

object Object {
    const val CYRILLIC_REGEX = "[а-яА-ЯёЁ]+"
    const val MAX_PHONE_NUMBER_LENGTH = 12
    val CATEGORIES = listOf(
        CategoryUI(
            "Смотреть все", ""
        ),
        CategoryUI(
            "Лицо", "face"
        ),
        CategoryUI(
            "Тело", "body"
        ),
        CategoryUI(
            "Загар", "suntan"
        ),
        CategoryUI(
            "Маски", "mask"
        )
    )

    val IMAGES_MAP = hashMapOf(
        "cbf0c984-7c6c-4ada-82da-e29dc698bb50" to listOf(
            R.drawable.product_image_6,
            R.drawable.product_image_5
        ),
        "54a876a5-2205-48ba-9498-cfecff4baa6e" to listOf(
            R.drawable.product_image_1,
            R.drawable.product_image_2
        ),
        "75c84407-52e1-4cce-a73a-ff2d3ac031b3" to listOf(
            R.drawable.product_image_5,
            R.drawable.product_image_6
        ),
        "16f88865-ae74-4b7c-9d85-b68334bb97db" to listOf(
            R.drawable.product_image_3,
            R.drawable.product_image_4
        ),
        "26f88856-ae74-4b7c-9d85-b68334bb97db" to listOf(
            R.drawable.product_image_2,
            R.drawable.product_image_3
        ),
        "15f88865-ae74-4b7c-9d81-b78334bb97db" to listOf(
            R.drawable.product_image_6,
            R.drawable.product_image_1
        ),
        "88f88865-ae74-4b7c-9d81-b78334bb97db" to listOf(
            R.drawable.product_image_4,
            R.drawable.product_image_3
        ),
        "55f58865-ae74-4b7c-9d81-b78334bb97db" to listOf(
            R.drawable.product_image_1,
            R.drawable.product_image_5
        ),
    )
}
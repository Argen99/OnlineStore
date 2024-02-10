package com.example.onlinestore.core.utils

import com.example.onlinestore.R
import com.example.onlinestore.ui.model.CategoryUI

object Object {
    const val CYRILLIC_REGEX = "[а-яА-ЯёЁ]+"
    const val MAX_PHONE_NUMBER_LENGTH = 12
    const val KEY_IS_AUTHORIZED = "key_auth"
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
}
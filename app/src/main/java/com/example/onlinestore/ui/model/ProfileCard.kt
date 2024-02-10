package com.example.onlinestore.ui.model

import androidx.annotation.DrawableRes

data class ProfileCard(
    val title: String,
    val value: Int? = null,
    @DrawableRes val icon: Int,
)
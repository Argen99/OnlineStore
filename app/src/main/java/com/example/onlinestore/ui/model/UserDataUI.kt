package com.example.onlinestore.ui.model

import com.example.data.core.DataMapper
import com.example.domain.model.UserDataModel
import kotlinx.serialization.Serializable

@Serializable
data class UserDataUI(
    val name: String,
    val surname: String,
    val phone: String
) : DataMapper<UserDataModel> {

    override fun toDomain() = UserDataModel(
        name = name,
        surname = surname,
        phone = phone
    )
}

fun UserDataModel.toUI() = UserDataUI(
    name = name,
    surname = surname,
    phone = phone
)
package com.example.data.model

import com.example.data.core.DataMapper
import com.example.domain.model.UserDataModel
import kotlinx.serialization.Serializable

@Serializable
data class UserDataDto(
    val name: String,
    val surname: String,
    val phone: String
) : DataMapper<UserDataModel> {

    override fun toDomain() = UserDataModel(name, surname, phone)

}

fun UserDataModel.toDto() = UserDataDto(name, surname, phone)

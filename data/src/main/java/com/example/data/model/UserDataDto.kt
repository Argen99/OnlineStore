package com.example.data.model

import com.example.data.core.DataMapper
import com.example.domain.model.UserData
import kotlinx.serialization.Serializable

@Serializable
data class UserDataDto(
    val name: String,
    val surname: String,
    val phone: String
) : DataMapper<UserData> {

    override fun toDomain() = UserData(name, surname, phone)

}

fun UserData.toDto() = UserDataDto(name, surname, phone)

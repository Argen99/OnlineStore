package com.example.domain.repository

import com.example.domain.model.UserDataModel

interface UserRepository {

    fun getUserData(): UserDataModel?
    fun saveUserData(data: UserDataModel)
    fun clearUserData()
}
package com.example.domain.repository

import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserData(): UserData
    fun saveUserData(data: UserData)
}
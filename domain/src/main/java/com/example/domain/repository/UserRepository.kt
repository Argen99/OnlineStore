package com.example.domain.repository

import com.example.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserData(): Flow<UserData>
    suspend fun saveUserData(data: UserData)
}
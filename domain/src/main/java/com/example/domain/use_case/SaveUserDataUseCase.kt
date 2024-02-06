package com.example.domain.use_case

import com.example.domain.model.UserData
import com.example.domain.repository.UserRepository

class SaveUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(data: UserData) = repository.saveUserData(data)
}
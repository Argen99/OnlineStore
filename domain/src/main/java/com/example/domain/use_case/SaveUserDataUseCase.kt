package com.example.domain.use_case

import com.example.domain.model.UserDataModel
import com.example.domain.repository.UserRepository

class SaveUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(data: UserDataModel) = repository.saveUserData(data)
}
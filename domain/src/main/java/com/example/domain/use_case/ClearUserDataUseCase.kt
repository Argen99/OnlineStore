package com.example.domain.use_case

import com.example.domain.repository.UserRepository

class ClearUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.clearUserData()
}
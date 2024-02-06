package com.example.domain.use_case

import com.example.domain.repository.UserRepository

class GetUserDataUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getUserData()
}
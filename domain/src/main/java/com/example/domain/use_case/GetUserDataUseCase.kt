package com.example.domain.use_case

import com.example.domain.repository.UserRepository

class GetUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.getUserData()
}
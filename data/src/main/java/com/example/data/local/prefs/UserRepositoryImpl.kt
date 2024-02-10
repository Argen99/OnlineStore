package com.example.data.local.prefs

import com.example.domain.model.UserDataModel
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(
    private val prefs: UserPreferences
): UserRepository {

    override fun getUserData(): UserDataModel? {
        return prefs.getUserData()
    }

    override fun saveUserData(data: UserDataModel) {
        prefs.saveUserData(data)
    }

    override fun clearUserData() {
        prefs.clearUserData()
    }
}
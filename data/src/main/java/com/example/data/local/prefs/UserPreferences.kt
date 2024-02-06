package com.example.data.local.prefs

import android.content.Context
import com.example.data.model.UserDataDto
import com.example.data.model.toDto
import com.example.domain.model.UserData
import com.example.domain.repository.UserRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserPreferences(context: Context) : UserRepository {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override fun getUserData(): UserData {
        val res = prefs.getString(USER_KEY, null)
        return Json.decodeFromString<UserDataDto>(res.orEmpty()).toDomain()
    }

    override fun saveUserData(data: UserData) {
        val jsonData = Json.encodeToString(data.toDto())
        prefs.edit().putString(USER_KEY, jsonData).apply()
    }

    companion object {
        const val USER_KEY = "user_key"
    }
}
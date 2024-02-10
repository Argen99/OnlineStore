package com.example.data.local.prefs

import android.content.Context
import com.example.data.model.UserDataDto
import com.example.data.model.toDto
import com.example.domain.model.UserDataModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun getUserData(): UserDataModel? {
        val res = prefs.getString(USER_KEY, null)
        if (!res.isNullOrEmpty()) {
            val obj = Json.decodeFromString<UserDataDto>(res)
            return obj.toDomain()
        }
        return null
    }

    fun saveUserData(data: UserDataModel) {
        val jsonData = Json.encodeToString(data.toDto())
        prefs.edit().putString(USER_KEY, jsonData).apply()
    }

    fun clearUserData() {
        prefs.edit().clear().apply()
    }

    companion object {
        const val USER_KEY = "user_key"
    }
}
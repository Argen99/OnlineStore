package com.example.data.local.data_store

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.core.Constants.USER_DATA_KEY
import com.example.data.model.UserDataDto
import com.example.data.model.toDto
import com.example.domain.model.UserData
import com.example.domain.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "PREFERENCES_NAME")

class DataStoreRepositoryImpl(private val context: Context) : UserRepository {

    override suspend fun getUserData(): Flow<UserData>  {
        val preferencesKey = stringPreferencesKey(USER_DATA_KEY)
        return try {
            context.dataStore.data
                .map { preferences ->
                    val res = Json.decodeFromString<UserDataDto>(preferences[preferencesKey].orEmpty()).toDomain()
                    res
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyFlow()
        }
    }

    override suspend fun saveUserData(data: UserData) {
        val preferencesKey = stringPreferencesKey(USER_DATA_KEY)
        val jsonData = Json.encodeToString(data.toDto())
        context.dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
            preferences[preferencesKey] = jsonData
        }
    }
}
package com.zekony.windichat.data.localStorage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zekony.windichat.di.SingletonModule.Companion.dataStore
import com.zekony.windichat.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserDatastore(
    private val context: Context
) {

    companion object {
        private val USER_KEY = stringPreferencesKey("user_token")
    }

    suspend fun saveUser(user: User) {
        val json = Json.encodeToString(user)
        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = json
        }
    }

    fun loadUser(): Flow<User?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_KEY]?.let { json ->
                Json.decodeFromString<User>(json)
            }
        }
    }

}
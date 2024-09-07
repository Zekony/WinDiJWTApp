package com.zekony.windichat.data.localStorage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zekony.windichat.di.SingletonModule.Companion.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(
    private val context: Context
) {

    private val ASS_TOKEN_KEY = stringPreferencesKey("ass_token")
    private val REF_TOKEN_KEY = stringPreferencesKey("ref_token")


    fun getAccessToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[ASS_TOKEN_KEY]
        }
    }

    suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[ASS_TOKEN_KEY] = token
        }
    }

    suspend fun deleteAccessToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(ASS_TOKEN_KEY)
        }
    }

    fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[REF_TOKEN_KEY]
        }
    }

    suspend fun saveRefreshToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[REF_TOKEN_KEY] = token
        }
    }

    suspend fun deleteRefreshToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(REF_TOKEN_KEY)
        }
    }
}
package com.islam.tasks.users.data.source.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.islam.tasks.users.domain.entity.UserEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserCache @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>,
    private val gson: Gson
) {

    suspend fun saveUsers(users: List<UserEntity>) {
        Result.runCatching {
            userDataStorePreferences.edit { preferences ->
                preferences[USERS_KEY] = gson.toJson(users)
            }
        }
    }

    suspend fun getUsers(): Result<String> {
        return Result.runCatching {
            val flow = userDataStorePreferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }.map { preferences ->
                    preferences[USERS_KEY]
                }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }

    companion object {
        private val USERS_KEY = stringPreferencesKey(
            name = "USERS_KEY"
        )
    }
}
package c.june.learning.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class MainRepository(private val dataStore: DataStore<Preferences>) {
    suspend fun saveUserNotificationState(state: Boolean) {
        dataStore.edit { pref ->
            pref[DataStoreKey.IS_NOTIFICATION_ON] = state
        }
    }

    suspend fun getUserNotificationState(): Flow<Boolean> {
        return dataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { prefs ->
                prefs[DataStoreKey.IS_NOTIFICATION_ON] ?: false
            }
    }
}
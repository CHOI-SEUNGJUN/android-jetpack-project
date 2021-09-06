package c.june.learning.data.source

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import c.june.learning.data.source.local.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    suspend fun <T> edit (key: Preferences.Key<T>, value: T) {
        dataStore.edit { pref ->
            pref[key] = value
        }
    }

    suspend fun <T> get (key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { prefs ->
                prefs[key] ?: defaultValue
            }
    }
}
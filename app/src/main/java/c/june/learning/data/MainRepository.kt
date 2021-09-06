package c.june.learning.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val dataStoreManager: DataStoreManager) {

    suspend fun saveUserNotificationState(state: Boolean) {
        dataStoreManager.edit(DataStoreKey.IS_NOTIFICATION_ON, state)
    }

    suspend fun getUserNotificationState(): Flow<Boolean> {
        return dataStoreManager.get(DataStoreKey.IS_NOTIFICATION_ON, false)
    }
}
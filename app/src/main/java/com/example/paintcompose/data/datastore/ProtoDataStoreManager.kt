package com.example.paintcompose.data.datastore

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow


private const val FILE_NAME_PROTO_DATASTORE = "user_settings.json"

private val Context.protoDataStore by dataStore(
    fileName = FILE_NAME_PROTO_DATASTORE,
    serializer = UserSettingsSerializer
)

class ProtoDataStoreManager(val context: Context) {

    suspend fun updateUserSettings(settings: UserSettings) {
        context.protoDataStore.updateData { currentData ->
            settings
        }
    }

    fun getUserSettings(): Flow<UserSettings> = context.protoDataStore.data

}
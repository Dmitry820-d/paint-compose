package com.example.paintcompose.data.repository

import com.example.paintcompose.data.datastore.ProtoDataStoreManager
import com.example.paintcompose.data.datastore.UserSettings
import kotlinx.coroutines.flow.Flow

class SettingsRepository(val protoDataStoreManager: ProtoDataStoreManager) {

    suspend fun getUserSettings(): Flow<UserSettings> {
        return protoDataStoreManager.getUserSettings()
    }

    suspend fun setSettings(settings: UserSettings) {
        protoDataStoreManager.updateUserSettings(settings = settings)
    }

}
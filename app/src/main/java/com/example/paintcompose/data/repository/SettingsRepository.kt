package com.example.paintcompose.data.repository

import com.example.paintcompose.data.datastore.ProtoDataStoreManager
import com.example.paintcompose.data.model.DrawSettingsData
import kotlinx.coroutines.flow.Flow

class SettingsRepository(val protoDataStoreManager: ProtoDataStoreManager) {

    fun getUserSettings(): Flow<DrawSettingsData> {
        return protoDataStoreManager.getUserSettings()
    }

    suspend fun setSettings(settings: DrawSettingsData) {
        protoDataStoreManager.updateUserSettings(settings = settings)
    }

}
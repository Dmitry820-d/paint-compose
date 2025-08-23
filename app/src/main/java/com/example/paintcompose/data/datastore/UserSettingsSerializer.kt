package com.example.paintcompose.data.datastore

import android.util.Log
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSettingsSerializer: Serializer<UserSettings> {
    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings {
        return try {
            Json.decodeFromString(
                deserializer = UserSettings.serializer(),
                //string = input.readBytes().toString()
                string = input.bufferedReader(charset = Charsets.UTF_8).use { it.readText() }
            )
        } catch (e: Exception){
            Log.d("MyTag", "error readFrom function for UserSettings = ${e.message}")
            defaultValue
        }


    }

    override suspend fun writeTo(
        t: UserSettings,
        output: OutputStream
    ) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = UserSettings.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
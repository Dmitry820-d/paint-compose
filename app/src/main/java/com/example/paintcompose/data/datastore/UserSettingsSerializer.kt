package com.example.paintcompose.data.datastore

import android.util.Log
import androidx.datastore.core.Serializer
import com.example.paintcompose.data.model.DrawSettingsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSettingsSerializer: Serializer<DrawSettingsData> {
    override val defaultValue: DrawSettingsData
        get() = DrawSettingsData()

    override suspend fun readFrom(input: InputStream): DrawSettingsData {
        return try {
            Json.decodeFromString(
                deserializer = DrawSettingsData.serializer(),
                //string = input.readBytes().toString()
                string = input.bufferedReader(charset = Charsets.UTF_8).use { it.readText() }
            )
        } catch (e: Exception){
            Log.d("MyTag", "error readFrom function for UserSettings = ${e.message}")
            defaultValue
        }


    }

    override suspend fun writeTo(
        t: DrawSettingsData,
        output: OutputStream
    ) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = DrawSettingsData.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
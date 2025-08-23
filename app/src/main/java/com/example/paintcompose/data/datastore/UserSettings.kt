package com.example.paintcompose.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val width: Float = 10f
)
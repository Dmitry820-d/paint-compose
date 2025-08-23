package com.example.paintcompose.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import kotlinx.serialization.Serializable

@Serializable
data class DrawSettingsData(
    val color: ULong = Color.Black.value,
    val width: Float = 5f,
    val brush: String = "Round"
)

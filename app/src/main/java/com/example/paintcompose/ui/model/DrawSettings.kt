package com.example.paintcompose.ui.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap

data class DrawSettings(
    val path: Path = Path(),
    val color: Color = Color.Black,
    val width: Float = 5f,
    val brush: StrokeCap = StrokeCap.Round
)
package com.example.paintcompose.ui.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.example.paintcompose.data.model.DrawSettingsData
import com.example.paintcompose.ui.model.DrawSettingsUI

class DrawSettingsDataToDrawSettingsUIMapper: (DrawSettingsData) -> DrawSettingsUI {
    override fun invoke(drawSettingsData: DrawSettingsData): DrawSettingsUI {
        return DrawSettingsUI(
            color = Color(drawSettingsData.color),
            width = drawSettingsData.width,
            brush = drawSettingsData.brush.toStrokeCap()
        )
    }
}


fun String.toStrokeCap(): StrokeCap = when(this.lowercase()){
    "round" -> { StrokeCap.Round }
    "square" -> { StrokeCap.Square }
    "butt" -> { StrokeCap.Butt }
    else -> { StrokeCap.Round }
}
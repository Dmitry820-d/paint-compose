package com.example.paintcompose.ui.mapper

import com.example.paintcompose.data.model.DrawSettingsData
import com.example.paintcompose.ui.model.DrawSettingsUI

class DrawSettingsUIToDrawSettingsDataMapper: (DrawSettingsUI) -> DrawSettingsData {
    override fun invoke(drawSettingsUI: DrawSettingsUI): DrawSettingsData {
        return DrawSettingsData(
            color = drawSettingsUI.color.value,
            width = drawSettingsUI.width,
            brush = drawSettingsUI.brush.toString()
        )
    }
}


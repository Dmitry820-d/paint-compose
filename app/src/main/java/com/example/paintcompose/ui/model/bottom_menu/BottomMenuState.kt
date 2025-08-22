package com.example.paintcompose.ui.model.bottom_menu

import androidx.compose.ui.graphics.Color

sealed class BottomMenuState {
    data class ColorState(val currentColor: Color): BottomMenuState()
    data class WidthState(val width: Int): BottomMenuState()
    data class BrushState(val brush: String): BottomMenuState()
    object None: BottomMenuState()
}
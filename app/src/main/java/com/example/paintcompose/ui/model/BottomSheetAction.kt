package com.example.paintcompose.ui.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

sealed class BottomSheetAction {
    data class UpdateColor(val color: Color): BottomSheetAction()
    data class UpdateWidth(val width: Float): BottomSheetAction()
    data class UpdateBrush(val brush: StrokeCap): BottomSheetAction()
}
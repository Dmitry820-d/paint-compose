package com.example.paintcompose.ui.model.bottom_menu

import androidx.annotation.DrawableRes
import com.example.paintcompose.R

sealed class BottomMenuItem(open val description: String, @DrawableRes open val resId: Int) {
    data class Color(
        override val description: String = "Color",
        @DrawableRes override val resId: Int = R.drawable.color
    ): BottomMenuItem(description, resId)


    data class Width(
        override val description: String = "Width",
        @DrawableRes override val resId: Int = R.drawable.width
        ): BottomMenuItem(description, resId)

    data class Brush(
        override val description: String = "Brush",
        @DrawableRes override val resId: Int = R.drawable.brush
    ): BottomMenuItem(description, resId)
}
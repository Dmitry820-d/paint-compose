package com.example.paintcompose.ui.navigation

import kotlinx.serialization.Serializable


sealed class Screen {
    @Serializable
    object DrawHomeScreen: Screen()

    @Serializable
    object ListImages: Screen()

    @Serializable
    object Favorite: Screen()
}
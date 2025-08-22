package com.example.paintcompose.ui.model.drawer_menu

import com.example.paintcompose.ui.navigation.Screen

sealed class DrawerMenuItem(open val screen: Screen, open val description: String) {
    data class Paint(override val screen: Screen = Screen.DrawHomeScreen, override val description: String): DrawerMenuItem(screen, description)
    data class ListImages(override val screen: Screen = Screen.ListImages, override val description: String): DrawerMenuItem(screen, description)
    data class Favorite(override val screen: Screen = Screen.Favorite, override val description: String): DrawerMenuItem(screen, description)
}
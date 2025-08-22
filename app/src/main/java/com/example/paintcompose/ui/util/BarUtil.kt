package com.example.paintcompose.ui.util

import androidx.compose.ui.graphics.StrokeCap
import com.example.paintcompose.ui.model.bottom_menu.BottomMenuItem
import com.example.paintcompose.ui.model.drawer_menu.DrawerMenuItem

object BarUtil {

    fun getListDrawerMenuItem(): List<DrawerMenuItem> =  listOf(
        DrawerMenuItem.Paint(description = "Создать рисунок"),
        DrawerMenuItem.ListImages(description = "Мои рисунки"),
        DrawerMenuItem.Favorite(description = "Избранные"),
    )

    fun getListBottomBarItem(): List<BottomMenuItem> = listOf(
        BottomMenuItem.Color(),
        BottomMenuItem.Width(),
        BottomMenuItem.Brush()
    )

    fun getListBrush(): List<StrokeCap> = listOf(
        StrokeCap.Round,
        StrokeCap.Square,
        StrokeCap.Butt
    )
}
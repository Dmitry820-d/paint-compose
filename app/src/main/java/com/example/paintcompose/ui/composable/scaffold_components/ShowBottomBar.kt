package com.example.paintcompose.ui.composable.scaffold_components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.paintcompose.ui.model.bottom_menu.BottomMenuItem
import com.example.paintcompose.ui.theme.colorBackground
import com.example.paintcompose.ui.theme.colorIcon
import com.example.paintcompose.ui.util.BarUtil

@Composable
fun BottomBar(
    onSelectedItem: (BottomMenuItem) -> Unit
) {
    val items = BarUtil.getListBottomBarItem()
    NavigationBar(
        containerColor = colorBackground
    ) {
        items.forEach { bottomMenuItem ->
            NavigationBarItem(
                modifier = Modifier.padding(2.dp),
                icon = {
                    Icon(
                        tint = colorIcon,
                        painter = painterResource(bottomMenuItem.resId),
                        contentDescription = null
                    )
                },
                selected = true,
                onClick = {
                    onSelectedItem(bottomMenuItem)
                }
            )
        }
    }
}
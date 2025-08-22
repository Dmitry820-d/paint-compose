package com.example.paintcompose.ui.composable.scaffold_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.paintcompose.R
import com.example.paintcompose.ui.theme.colorBackground
import com.example.paintcompose.ui.theme.colorIcon
import com.example.paintcompose.ui.theme.colorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTopAppBar(
    onOpenDrawer: () -> Unit,
    onSaveImage: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorBackground
        ),
        title = {
            Text(
                text = "Paint Compose",
                color = colorText
            )
        },

        navigationIcon = {
            IconButton(
                onClick = { onOpenDrawer() },
            ){
                Icon(
                    tint = colorIcon,
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onSaveImage() },
            ){
                Icon(
                    modifier = Modifier.size(30.dp),
                    tint = colorIcon,
                    painter = painterResource(R.drawable.save),
                    contentDescription = null,
                )
            }
        },
    )
}
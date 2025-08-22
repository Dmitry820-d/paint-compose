package com.example.paintcompose.ui.composable.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import com.example.paintcompose.ui.composable.ShowImageItem
import com.example.paintcompose.ui.model.ImageUI

@Composable
fun ShowListImage(list: List<ImageUI>) {
    Box(modifier = Modifier.padding(10.dp)){
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(list) { image ->
                ShowImageItem(image){ image ->

                }
            }
        }
    }
}



package com.example.paintcompose.ui.composable.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.paintcompose.ui.viewmodel.MainViewModel

@Composable
fun ShowFavoriteImage(mainViewModel: MainViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = mainViewModel.currentDrawSettingsState.value.width.toString()
        )
    }
}
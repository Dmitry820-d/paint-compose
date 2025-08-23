package com.example.paintcompose

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.datastore.core.DataStore
import com.example.paintcompose.ui.composable.ShowScaffold
import com.example.paintcompose.ui.theme.PaintComposeTheme
import com.example.paintcompose.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.main_activity)

        val composeView = findViewById<ComposeView>(R.id.compose_view)

        composeView.setContent {


            PaintComposeTheme {
                ShowScaffold(
                    mainViewModel = mainViewModel,
                    composeView = composeView
                )
            }
        }
    }
}



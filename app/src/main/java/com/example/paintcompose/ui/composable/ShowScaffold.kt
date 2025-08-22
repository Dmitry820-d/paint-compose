package com.example.paintcompose.ui.composable

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.drawToBitmap
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paintcompose.ui.composable.scaffold_components.BottomBar
import com.example.paintcompose.ui.composable.scaffold_components.ShowDrawerLayout
import com.example.paintcompose.ui.composable.scaffold_components.ShowTopAppBar
import com.example.paintcompose.ui.composable.screens.ShowCanvas
import com.example.paintcompose.ui.composable.screens.ShowListImage
import com.example.paintcompose.ui.navigation.Screen
import com.example.paintcompose.ui.viewmodel.MainViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowScaffold(
    mainViewModel: MainViewModel,
    composeView: ComposeView
) {

    var topCrop = 0
    var bottomCrop = 0

    val scaffoldState = rememberScaffoldState()
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val currentPathSettingsState = remember { mainViewModel.currentDrawSettingsState }
    val listImagesState = remember { mainViewModel.listImageState }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            ShowDrawerLayout { drawerMenuItem ->
                navController.navigate(drawerMenuItem.screen)
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        topBar = {
            ShowTopAppBar(
                onOpenDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onSaveImage = {
                    scope.launch(Dispatchers.IO) {
                        delay(100)
                        try {
                            var originalBitmap: Bitmap
                            withContext(Dispatchers.Main) {
                                originalBitmap = composeView.drawToBitmap()
                            }

                            mainViewModel.saveImageToPublicStorage(
                                originalBitmap = originalBitmap,
                                name = "My Image",
                                origWidth = originalBitmap.width,
                                origHeight = originalBitmap.height,
                                topCrop = 348,
                                bottomCrop = bottomCrop
                            )
                        } catch(e: Exception) {
                            Log.d("MyTag", "error message = ${e.message}")
                        }
                    }.invokeOnCompletion {


                        //Toast.makeText(composeView.context, "fs", Toast.LENGTH_LONG).show()
                    }
                }
            )
        },
        content = { innerPadding ->
            topCrop = LocalDensity.current.run { innerPadding.calculateTopPadding().roundToPx() }
            bottomCrop = LocalDensity.current.run { innerPadding.calculateBottomPadding().roundToPx() }

            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.DrawHomeScreen
                )
                {
                    composable<Screen.DrawHomeScreen> { navBackStackEntry ->
                        ShowCanvas(currentDrawSettingsState = currentPathSettingsState)
                    }
                    composable<Screen.ListImages> { navBackStackEntry ->
                        ShowListImage(listImagesState.value)
                    }
                    composable<Screen.Favorite> { navBackEntry ->
                        //not implemented
                    }
                }
            }

            if (mainViewModel.showModalBottomSheet.value) {
                ShowModalBottomSheet(
                    currentDrawSettings = mainViewModel.currentDrawSettingsState,
                    state = mainViewModel.bottomMenuState,
                    modalBottomSheetState = modalBottomSheetState,
                    bottomSheetAction = { bottomSheetAction ->
                        mainViewModel.updateSettings(bottomSheetAction)

//                        scope.launch {
//                            modalBottomSheetState.hide()
//                        }.invokeOnCompletion {
//                            if (!modalBottomSheetState.isVisible) {
//                                mainViewModel.updateModalBottomSheet(false)
//                            }
//                        }
                    },
                    onDismissModalBottomSheet = {
                        mainViewModel.updateModalBottomSheet(false)
                    }
                )
            }
        },
        bottomBar = {
            BottomBar { bottomMenuItem ->
                mainViewModel.updateModalBottomSheet(true)
                mainViewModel.updateBottomMenuState(bottomMenuItem)
            }
        }
    )

}
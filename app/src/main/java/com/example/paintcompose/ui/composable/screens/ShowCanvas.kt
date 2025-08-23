package com.example.paintcompose.ui.composable.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.paintcompose.ui.model.DrawSettingsUI

@Composable
fun ShowCanvas(
    currentDrawSettingsState: MutableState<DrawSettingsUI>,
) {

    var tempPath = Path()
    val listDrawSettingsState = remember {
        mutableStateListOf(DrawSettingsUI())
    }

    Box {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 3.dp)
                .background(color = Color.White)
                .pointerInput(true) {
                    detectDragGestures(
                        onDragStart = {
                            tempPath = Path()
                        },
                        onDragEnd = {
                            listDrawSettingsState.add(currentDrawSettingsState.value.copy(path = tempPath))
                        }
                    ) { change, dragAmount ->
                        tempPath.moveTo(
                            change.position.x - dragAmount.x,
                            change.position.y - dragAmount.y
                        )
                        tempPath.lineTo(
                            change.position.x,
                            change.position.y
                        )

                        if (listDrawSettingsState.isNotEmpty()) listDrawSettingsState.removeAt(
                            listDrawSettingsState.size - 1
                        )
                        listDrawSettingsState.add(currentDrawSettingsState.value.copy(path = tempPath))
                    }
                }
        ) {
            listDrawSettingsState.forEach { pathSettings ->

                drawPath(
                    path = pathSettings.path,
                    color = pathSettings.color,
                    style = Stroke(
                        width = pathSettings.width,
                        cap = pathSettings.brush
                    ),
                )
            }
        }
    }
}





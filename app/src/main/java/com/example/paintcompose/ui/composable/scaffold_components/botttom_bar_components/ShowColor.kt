package com.example.paintcompose.ui.composable.scaffold_components.botttom_bar_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.paintcompose.ui.model.DrawSettingsUI
import com.example.paintcompose.ui.theme.colorBackground
import com.example.paintcompose.ui.theme.colorBorder
import com.example.paintcompose.ui.theme.colorText
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ShowColor(
    currentDrawSettings: MutableState<DrawSettingsUI>,
    onSelectedItemColor: (Color) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){

        ShowColorPicker(
            currentDrawSettings = currentDrawSettings,
        ) { selectedColor ->
            onSelectedItemColor(selectedColor)
        }
//        LazyRow(
//            modifier = Modifier.align(Alignment.TopCenter)
//        ) {
//            val listColors = ColorUtils.getColors()
//            items(listColors) { color ->
//                ItemColor(color){
//                    onSelectedItemColor(color)
//                }
//            }
//        }
    }
}

@Composable
private fun ItemColor(
    color: Color,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(50.dp)
            .padding(10.dp)
            .background(color = color, shape = RoundedCornerShape(20.dp))
            .clickable {
                onSelected()
            }

    )
}


@Composable
private fun ShowColorPicker(
    currentDrawSettings: MutableState<DrawSettingsUI>,
    onSelectedItemColor: (Color) -> Unit
){

    val colorPickerController = rememberColorPickerController()
    val currentHex = remember { mutableStateOf("ffffffff") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()

            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ){
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = currentHex.value,
                        color = colorText
                    )
                    AlphaTile(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                brush = Brush.linearGradient(
                                    listOf(colorBorder, colorBackground)
                                ),
                                shape = RoundedCornerShape(6.dp))
                            .size(75.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        controller = colorPickerController
                    )
                }
            }
            AlphaSlider(
                initialColor = currentDrawSettings.value.color,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(30.dp),
                controller = colorPickerController,
                borderRadius = 12.dp,
                borderSize = 7.dp,
                borderColor = colorBackground,
            )
            BrightnessSlider(
                initialColor = currentDrawSettings.value.color,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(30.dp),
                controller = colorPickerController,
                borderRadius = 12.dp,
                borderSize = 7.dp,
                borderColor = colorBackground,
            )
        }
        Box(
            modifier = Modifier.weight(1f)
        ){
            HsvColorPicker(
                initialColor = currentDrawSettings.value.color,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .padding(5.dp),
                controller = colorPickerController,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    currentHex.value = colorEnvelope.hexCode
                    onSelectedItemColor(colorPickerController.selectedColor.value)
                }
            )
        }
    }
}
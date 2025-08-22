package com.example.paintcompose.ui.composable.scaffold_components.botttom_bar_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.paintcompose.ui.model.DrawSettings
import com.example.paintcompose.ui.theme.colorIcon
import com.example.paintcompose.ui.theme.colorText
import com.example.paintcompose.ui.util.ColorUtils

@Composable
fun ShowWidth(
    currentDrawSettings: MutableState<DrawSettings>,
    onSelectedItemWidth: (Float) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .height(200.dp)
    ){

        var sliderPosition = remember { mutableFloatStateOf(currentDrawSettings.value.width / 100f) }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.height(120.dp).fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)

                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "${(sliderPosition.floatValue * 100).toInt()}",
                        color = colorText,
                        fontSize = 40.sp
                    )
                }

                Spacer(Modifier.size(15.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = colorIcon.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "px",
                        color = colorText,
                        fontSize = 25.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(110.dp)
                ){
                    Box(
                        modifier = Modifier
                            .size(((sliderPosition.floatValue * 100).toInt() / 2).dp)
                            .align(Alignment.Center)
                            .background(
                                color = currentDrawSettings.value.color,
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                }

            }

            Spacer(Modifier.size(10.dp))
            Slider(
                value = sliderPosition.floatValue,
                onValueChange = {
                    sliderPosition.floatValue = it
                    onSelectedItemWidth(it * 100)
                }

            )
        }
    }
}
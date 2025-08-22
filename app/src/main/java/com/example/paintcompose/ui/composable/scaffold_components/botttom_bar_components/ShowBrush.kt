package com.example.paintcompose.ui.composable.scaffold_components.botttom_bar_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.paintcompose.ui.theme.colorIcon
import com.example.paintcompose.ui.util.BarUtil

@Composable
fun ShowBrush(
    onSelectedItemBrush: (StrokeCap) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            val listBrush = BarUtil.getListBrush()
            listBrush.forEach {
                ItemBrush {
                    onSelectedItemBrush(StrokeCap.Square)
                }
            }
        }
    }
}


@Composable
private fun ItemBrush(
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .padding(10.dp)
            .background(
                color = colorIcon,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable {
                onSelected()
            }
    )
}

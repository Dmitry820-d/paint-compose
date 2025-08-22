package com.example.paintcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.paintcompose.ui.model.ImageUI
import com.example.paintcompose.ui.theme.colorBackground
import com.example.paintcompose.ui.theme.colorText

@Composable
fun ShowImageItem(
    image: ImageUI,
    onClickItem: (ImageUI) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(top = 4.dp, start = 3.dp, end = 3.dp)
            .background(color = colorBackground, shape = RoundedCornerShape(7.dp))
            .padding(3.dp)
            .clickable {
                onClickItem(image)
            }

    ){
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                modifier = Modifier.weight(.9f,),
                model = image.uri,
                contentDescription = image.name
            )
            Box(
                modifier = Modifier
                    .weight(.1f)
                    .fillMaxWidth(),
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = image.name,
                    color = colorText
                )
            }
        }
    }
}
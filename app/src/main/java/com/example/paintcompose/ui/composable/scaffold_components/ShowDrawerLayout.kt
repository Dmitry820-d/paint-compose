package com.example.paintcompose.ui.composable.scaffold_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.paintcompose.R
import com.example.paintcompose.ui.model.drawer_menu.DrawerMenuItem
import com.example.paintcompose.ui.theme.colorBackground
import com.example.paintcompose.ui.theme.colorButton
import com.example.paintcompose.ui.theme.colorText
import com.example.paintcompose.ui.util.BarUtil.getListDrawerMenuItem

@Composable
fun ShowDrawerLayout(
    onClick: (DrawerMenuItem) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorBackground)
    ) {
        Header()
        Spacer(Modifier.size(10.dp))
        Body { drawerMenuItem ->
            onClick(drawerMenuItem)
        }
    }
}




@Composable
private fun Header(){
    Image(
        modifier = Modifier.height(300.dp),
        painter = painterResource(R.drawable.image_1),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun Body(
    onClick: (DrawerMenuItem) -> Unit
){
    val listItems = getListDrawerMenuItem()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(

        ){
            listItems.forEach { drawerMenuItem ->
                ListItem(drawerMenuItem = drawerMenuItem) {
                    onClick(drawerMenuItem)
                }
            }
        }
    }
}


@Composable
private fun ListItem(
    drawerMenuItem: DrawerMenuItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(4.dp)
            .background(colorButton)
            .clickable {
                onClick()
            }

    ){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = drawerMenuItem.description,
            color = colorText
        )
    }
}
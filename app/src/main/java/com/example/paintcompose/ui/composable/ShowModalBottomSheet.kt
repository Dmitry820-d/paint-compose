package com.example.paintcompose.ui.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.paintcompose.ui.composable.scaffold_components.botttom_bar_components.ShowBrush
import com.example.paintcompose.ui.composable.scaffold_components.botttom_bar_components.ShowColor
import com.example.paintcompose.ui.composable.scaffold_components.botttom_bar_components.ShowWidth
import com.example.paintcompose.ui.model.BottomSheetAction
import com.example.paintcompose.ui.model.DrawSettingsUI
import com.example.paintcompose.ui.model.bottom_menu.BottomMenuState
import com.example.paintcompose.ui.theme.colorBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowModalBottomSheet(
    currentDrawSettings: MutableState<DrawSettingsUI>,
    state: MutableState<BottomMenuState>,
    modalBottomSheetState: SheetState,
    bottomSheetAction: (BottomSheetAction) -> Unit,
    onDismissModalBottomSheet: () -> Unit
) {
    ModalBottomSheet(
        containerColor = colorBackground,
        onDismissRequest = {
            onDismissModalBottomSheet()
        },
        sheetState = modalBottomSheetState
    ) {
        when(state.value){
            is BottomMenuState.ColorState -> {
                ShowColor(
                    currentDrawSettings = currentDrawSettings,
                ) { color ->
                    bottomSheetAction(BottomSheetAction.UpdateColor(color))
                }
            }
            is BottomMenuState.WidthState -> {
                ShowWidth(
                    currentDrawSettings = currentDrawSettings,
                ) { width ->
                    bottomSheetAction(BottomSheetAction.UpdateWidth(width))
                }
            }
            is BottomMenuState.BrushState -> {
                ShowBrush { strokeCap ->
                    bottomSheetAction(BottomSheetAction.UpdateBrush(strokeCap))
                }
            }
            is BottomMenuState.None -> {}
        }
    }
}









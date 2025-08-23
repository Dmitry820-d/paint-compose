package com.example.paintcompose.ui.viewmodel

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paintcompose.data.repository.ImageRepository
import com.example.paintcompose.data.repository.SettingsRepository
import com.example.paintcompose.ui.mapper.DrawSettingsDataToDrawSettingsUIMapper
import com.example.paintcompose.ui.mapper.DrawSettingsUIToDrawSettingsDataMapper
import com.example.paintcompose.ui.model.bottom_menu.BottomMenuState
import com.example.paintcompose.ui.model.bottom_menu.BottomMenuItem
import com.example.paintcompose.ui.model.BottomSheetAction
import com.example.paintcompose.ui.model.ImageUI
import com.example.paintcompose.ui.model.DrawSettingsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val imageRepository: ImageRepository,
    private val settingsRepository: SettingsRepository,
    private val drawSettingsDataToUIMapper: DrawSettingsDataToDrawSettingsUIMapper,
    private val drawSettingUIToDataMapper: DrawSettingsUIToDrawSettingsDataMapper
): ViewModel() {

    val listImageState = mutableStateOf<List<ImageUI>>(emptyList())


    init {
        loadImages()
        getUserSettingsFromDataStore()
    }

    val bottomMenuState = mutableStateOf<BottomMenuState>(BottomMenuState.None)
    val currentDrawSettingsState = mutableStateOf(DrawSettingsUI())
    val showModalBottomSheet = mutableStateOf(false)

    fun updateSettings(bottomSheetAction: BottomSheetAction){
        when(bottomSheetAction) {
            is BottomSheetAction.UpdateColor -> { updateColor(bottomSheetAction.color) }
            is BottomSheetAction.UpdateWidth -> { updateWidth(bottomSheetAction.width) }
            is BottomSheetAction.UpdateBrush -> { updateBrush(bottomSheetAction.brush) }
        }
    }

    private fun getUserSettingsFromDataStore(){
        viewModelScope.launch {
            settingsRepository.getUserSettings().collect { drawSettings ->
                currentDrawSettingsState.value = drawSettingsDataToUIMapper(drawSettings)
            }
        }
    }




    private fun updateColor(color: Color) {
        val drawSettingsData = drawSettingUIToDataMapper(currentDrawSettingsState.value.copy(color = color))
        viewModelScope.launch {
            settingsRepository.setSettings(settings = drawSettingsData)
        }
    }

    private fun updateWidth(width: Float) {
        val resultWidth = if(width < 1f) 1f else width

        val drawSettingsData = drawSettingUIToDataMapper(currentDrawSettingsState.value.copy(width = resultWidth))
        viewModelScope.launch {
            settingsRepository.setSettings(settings = drawSettingsData)
        }
    }

    private fun updateBrush(cap: StrokeCap) {
        val drawSettingsData = drawSettingUIToDataMapper(currentDrawSettingsState.value.copy(brush = cap))
        viewModelScope.launch {
            settingsRepository.setSettings(settings = drawSettingsData)
        }
    }

    fun updateModalBottomSheet(isVisible: Boolean) {
        showModalBottomSheet.value = isVisible
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveImageToPublicStorage(originalBitmap: Bitmap, name: String, origWidth: Int, origHeight: Int, topCrop: Int, bottomCrop: Int){

        val croppedWidth = origWidth
        val croppedHeight = origHeight - topCrop - bottomCrop

        Log.d("MyTag", "${topCrop} ${bottomCrop}")

        // Создаем новый Bitmap, вырезая центральную часть
        val croppedBitmap = Bitmap.createBitmap(
            originalBitmap,
            0,               // Левая граница (начало слева)
            topCrop,         // Верхняя граница (скольжение вниз)
            croppedWidth,    // Ширина результирующего изображения
            croppedHeight    // Высота результирующего изображения
        )

        viewModelScope.launch {
            imageRepository.saveImage(croppedBitmap, name)
        }
    }

    fun loadImages() {
        viewModelScope.launch {
            listImageState.value = imageRepository.loadImages().map { ImageUI(it.uri, it.name) }
        }
    }

    fun updateBottomMenuState(item: BottomMenuItem) {

        when(item){
            is BottomMenuItem.Color -> bottomMenuState.value = BottomMenuState.ColorState(Color.Black)
            is BottomMenuItem.Width -> bottomMenuState.value = BottomMenuState.WidthState(10)
            is BottomMenuItem.Brush -> bottomMenuState.value = BottomMenuState.BrushState("")
        }
    }
}





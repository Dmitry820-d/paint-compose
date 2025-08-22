package com.example.paintcompose.data

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

class ImageRepository(val imageRecorder: ImageRecorder, val imageLoader: ImageLoader) {
    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun saveImage(bitmap: Bitmap, name: String){
        imageRecorder.saveImageToPublicStorage(bitmap = bitmap, name = name)
    }

    suspend fun loadImages(): List<ImageFile> {
        return imageLoader.loadImages()
    }

    data class ImageFile(val uri: Uri, val name: String)
}
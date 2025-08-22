package com.example.paintcompose.data

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import javax.inject.Inject

class ImageRecorder @Inject constructor(val context: Context) {
    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun saveImageToPublicStorage(bitmap: Bitmap, name: String) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$name.jpg")      // Имя файла
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")           // MIME-тип
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // Путь к каталогу
        }

        val resolver = context.contentResolver
        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val item = resolver.insert(collection, contentValues)

        item?.let { uri ->
            resolver.openOutputStream(uri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
        }
    }
}
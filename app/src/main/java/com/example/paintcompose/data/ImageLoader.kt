package com.example.paintcompose.data

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.example.paintcompose.data.repository.ImageRepository.ImageFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageLoader @Inject constructor(val context: Context) {

    @SuppressLint("Range")
    suspend fun loadImages(): List<ImageFile> = withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
        val selectionArgs = arrayOf("%Pictures%")

        resolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
            val result = mutableListOf<ImageFile>()
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                val name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                val uri = ContentUris.withAppendedId(collection, id)
                result.add(ImageFile(uri, name))
            }
            result
        } ?: emptyList()
    }
}
package com.example.paintcompose.di

import android.content.Context
import com.example.paintcompose.data.ImageLoader
import com.example.paintcompose.data.ImageRecorder
import com.example.paintcompose.data.datastore.ProtoDataStoreManager
import com.example.paintcompose.data.repository.ImageRepository
import com.example.paintcompose.data.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {

    companion object {

        @Provides
        @Singleton
        fun provideImageRecorder(@ApplicationContext context: Context): ImageRecorder {
            return ImageRecorder(context)
        }

        @Provides
        @Singleton
        fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
            return ImageLoader(context)
        }

        @Provides
        @Singleton
        fun provideImageRepository(imageRecorder: ImageRecorder, imageLoader: ImageLoader) : ImageRepository {
            return ImageRepository(
                imageRecorder = imageRecorder,
                imageLoader = imageLoader
            )
        }

        @Provides
        @Singleton
        fun provideProtoDataStoreManager(@ApplicationContext context: Context): ProtoDataStoreManager {
            return ProtoDataStoreManager(context = context)
        }

        @Provides
        @Singleton
        fun provideSettingsRepository(protoDataStoreManager: ProtoDataStoreManager): SettingsRepository {
            return SettingsRepository(protoDataStoreManager = protoDataStoreManager)
        }

    }
}
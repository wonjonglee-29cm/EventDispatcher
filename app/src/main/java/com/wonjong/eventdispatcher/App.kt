package com.wonjong.eventdispatcher

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
@HiltAndroidApp
class App : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader = ImageLoader
        .Builder(this)
        .memoryCache {
            MemoryCache.Builder(this)
                .maxSizePercent(0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(cacheDir.resolve("coil_image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .crossfade(true)
        .build()
}
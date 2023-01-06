package com.wonjong.eventdispatcher.event

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */

@Module
@InstallIn(SingletonComponent::class)
object EventTrackerModule {

    @Provides
    @Singleton
    fun provideEventTracker(@ApplicationContext context: Context): EventTracker = EventTrackerImpl(context)
}
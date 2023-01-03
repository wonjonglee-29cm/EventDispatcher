package com.wonjong.eventdispatcher.data.remote

import com.wonjong.eventdispatcher.data.remote.api.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}
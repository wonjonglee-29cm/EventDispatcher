package com.wonjong.eventdispatcher.data.repositoryImpl

import com.wonjong.eventdispatcher.data.remote.api.PostService
import com.wonjong.eventdispatcher.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindPost(postService: PostService): PostRepository = PostRepositoryImpl(postService)
}
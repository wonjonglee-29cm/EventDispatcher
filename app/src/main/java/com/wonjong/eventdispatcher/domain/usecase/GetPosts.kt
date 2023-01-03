package com.wonjong.eventdispatcher.domain.usecase

import com.wonjong.eventdispatcher.domain.entity.PostEntity
import com.wonjong.eventdispatcher.domain.repository.PostRepository
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
class GetPosts @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(): Result<List<PostEntity>> = runCatching {
        postRepository.getPosts()
    }
}
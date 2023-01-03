package com.wonjong.eventdispatcher.data.repositoryImpl.mapper

import com.wonjong.eventdispatcher.data.model.PostRaw
import com.wonjong.eventdispatcher.domain.entity.PostEntity

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
class PostMapper {
    fun to(from: PostRaw): PostEntity = PostEntity(
        id = from.id,
        body = from.body,
        title = from.title,
        userId = from.userId
    )
}
package com.wonjong.eventdispatcher.data.repositoryImpl.mapper

import android.graphics.Color
import com.wonjong.eventdispatcher.data.model.PostRaw
import com.wonjong.eventdispatcher.domain.entity.PostEntity
import java.util.*

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
class PostMapper {
    fun to(from: PostRaw): PostEntity {
        val rnd = Random()
        return PostEntity(
            id = from.id,
            body = from.body,
            title = from.title,
            colorInt = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)),
            userId = from.userId
        )
    }
}
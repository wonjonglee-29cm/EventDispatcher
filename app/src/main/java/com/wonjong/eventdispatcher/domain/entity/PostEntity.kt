package com.wonjong.eventdispatcher.domain.entity

import androidx.annotation.ColorInt

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
data class PostEntity(
    val id: Int,
    val body: String,
    val title: String,
    @ColorInt val colorInt: Int,
    val userId: Int
)

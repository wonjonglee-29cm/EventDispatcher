package com.wonjong.eventdispatcher.domain.entity

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
data class PostEntity(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)

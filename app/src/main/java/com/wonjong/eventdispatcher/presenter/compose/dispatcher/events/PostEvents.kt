package com.wonjong.eventdispatcher.presenter.compose.dispatcher.events

import com.wonjong.eventdispatcher.event.dispatcher.Events

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
sealed interface PostEvents : Events {
    data class ClickItem(
        val id: Int,
        val userId: Int,
        val title: String
    ) : PostEvents
}
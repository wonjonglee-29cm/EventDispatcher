package com.wonjong.eventdispatcher.presenter.compose.dispatcher

import com.wonjong.eventdispatcher.event.EventTracker
import com.wonjong.eventdispatcher.event.dispatcher.EventDispatcher
import com.wonjong.eventdispatcher.event.dispatcher.Events
import com.wonjong.eventdispatcher.event.dispatcher.post.PostEventDispatcherExt
import com.wonjong.eventdispatcher.event.type.EventTrackingType
import com.wonjong.eventdispatcher.presenter.compose.ComposeViewModel
import com.wonjong.eventdispatcher.presenter.compose.dispatcher.events.PostEvents
import org.json.JSONObject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-10
 */
class ComposeEventDispatcher(
    private val viewModel: ComposeViewModel,
    eventTracker: EventTracker
) : EventDispatcher, PostEventDispatcherExt(eventTracker) {
    override fun dispatchEvent(events: Events) {
        when (events) {
            is PostEvents.ClickItem -> {
                sendClickItemEvent(events)
                viewModel.showToast(events.title)
            }
        }
    }

    private fun sendClickItemEvent(event: PostEvents.ClickItem) {
        val params = JSONObject()
            .putId(event.id)
            .putUserId(event.userId)
            .putTitle(event.title)

        sendEvent(EventTrackingType.CLICK_ITEM)(params)
    }
}
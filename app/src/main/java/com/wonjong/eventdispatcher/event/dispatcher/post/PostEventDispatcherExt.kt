package com.wonjong.eventdispatcher.event.dispatcher.post

import com.wonjong.eventdispatcher.event.EventTracker
import com.wonjong.eventdispatcher.event.code.EventTrackingCode
import com.wonjong.eventdispatcher.event.type.EventTrackingType
import org.json.JSONObject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
abstract class PostEventDispatcherExt(
    eventTracker: EventTracker
) {
    protected val sendEvent: (eventType: EventTrackingType) -> (params: JSONObject) -> Unit = { eventType ->
        { params ->
            eventTracker.send(eventType = eventType, params = params)
        }
    }

    protected fun JSONObject.putId(id: Int): JSONObject = apply {
        put(EventTrackingCode.ID, id)
    }

    protected fun JSONObject.putUserId(userId: Int): JSONObject = apply {
        put(EventTrackingCode.USER_ID, userId)
    }

    protected fun JSONObject.putTitle(title: String): JSONObject = apply {
        put(EventTrackingCode.TITLE, title)
    }
}
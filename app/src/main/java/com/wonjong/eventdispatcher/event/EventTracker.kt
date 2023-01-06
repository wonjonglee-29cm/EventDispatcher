package com.wonjong.eventdispatcher.event

import com.wonjong.eventdispatcher.event.type.EventTrackingType
import org.json.JSONObject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
interface EventTracker {
    fun send(eventType: EventTrackingType, params: JSONObject? = null): Unit
}
package com.wonjong.eventdispatcher.event

import android.content.Context
import com.wonjong.eventdispatcher.event.type.EventTrackingType
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
class EventTrackerImpl @Inject constructor(@ApplicationContext private val context: Context) : EventTracker {
    override fun send(eventType: EventTrackingType, params: JSONObject?) {
        eventType.send(context = context, params = params)
    }
}
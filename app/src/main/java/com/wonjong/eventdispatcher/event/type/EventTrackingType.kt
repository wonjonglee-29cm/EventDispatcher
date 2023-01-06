package com.wonjong.eventdispatcher.event.type

import android.content.Context
import android.util.Log
import com.wonjong.eventdispatcher.event.code.EventTrackingCode
import org.json.JSONObject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
enum class EventTrackingType(@EventTrackingCode.Name eventName: String) {
    CLICK_ITEM(EventTrackingCode.CLICK.add(EventTrackingCode.ITEM)) {
        override fun send(context: Context, params: JSONObject?) {
            Log.d(EventTrackingCode.CLICK.add(EventTrackingCode.ITEM), params.toString())
        }
    };

    abstract fun send(context: Context, params: JSONObject?)
}

private fun String.add(addString: String, operator: String = "_"): String = this + operator + addString
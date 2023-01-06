package com.wonjong.eventdispatcher.event.dispatcher

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
interface EventDispatcher {
    fun dispatchEvent(events: Events)
}
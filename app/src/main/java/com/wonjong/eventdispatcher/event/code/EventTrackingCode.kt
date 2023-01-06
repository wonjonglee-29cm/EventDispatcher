package com.wonjong.eventdispatcher.event.code

import androidx.annotation.StringDef

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
object EventTrackingCode {
    @Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
    @StringDef(
        CLICK,
        ITEM
    )
    annotation class Name

    const val CLICK = "click"
    const val ITEM = "item"

    @Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
    @StringDef(
        ID,
        USER_ID,
        TITLE
    )
    annotation class Param

    const val ID = "id"
    const val USER_ID = "userId"
    const val TITLE = "title"
}
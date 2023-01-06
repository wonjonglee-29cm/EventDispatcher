package com.wonjong.eventdispatcher.presenter.utils

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
sealed class LCE<out T> {

    open val data: T? = null

    abstract fun <R> map(f: (T) -> R): LCE<R>

    inline fun doOnData(f: (T) -> Unit) {
        if (this is Content) {
            f(data)
        }
    }

    object Loading : LCE<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): LCE<R> = this
    }

    data class Content<out T>(
        override val data: T
    ) : LCE<T>() {
        override fun <R> map(f: (T) -> R): LCE<R> = Content(f(data))
    }

    data class Error(
        val throwable: Throwable = Exception("default LCE throwable"),
        val message: String = ""
    ) : LCE<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): LCE<R> = this
    }
}
package com.wonjong.eventdispatcher.presenter.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
class IdBasedDiffCallback<T : Any>(
    private val getIdOf: (T) -> Int
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        getIdOf(oldItem) == getIdOf(newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}
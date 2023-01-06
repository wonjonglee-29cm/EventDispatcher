package com.wonjong.eventdispatcher.presenter.tabs

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
class TabPageAdapter(
    private val activity: AppCompatActivity,
    private val tabs: StateFlow<List<TabType>>,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = tabs.value.size

    override fun createFragment(position: Int): Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
        activity.classLoader,
        tabs.value[position].fragmentClass.name.toString()
    )
}
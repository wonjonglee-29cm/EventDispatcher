package com.wonjong.eventdispatcher.presenter.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.wonjong.eventdispatcher.presenter.mvi.MviFragment
import com.wonjong.eventdispatcher.presenter.mvvm.MvvmFragment
import com.wonjong.eventdispatcher.presenter.mvvm_intent.MvvmIntentFragment

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
class TabFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment = when (loadFragmentClass(classLoader, className)) {
        TabType.MVI.fragmentClass -> MviFragment()
        TabType.MVVM.fragmentClass -> MvvmFragment()
        TabType.MVVM_INTENT.fragmentClass -> MvvmIntentFragment()
        else -> throw IllegalArgumentException("Unknown Fragment : $className")
    }
}
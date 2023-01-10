package com.wonjong.eventdispatcher.presenter.tabs

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.wonjong.eventdispatcher.R
import com.wonjong.eventdispatcher.presenter.compose.ComposeFragment
import com.wonjong.eventdispatcher.presenter.mvi.MviFragment
import com.wonjong.eventdispatcher.presenter.mvvm.MvvmFragment
import com.wonjong.eventdispatcher.presenter.mvvm_intent.MvvmIntentFragment

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
enum class TabType(@StringRes val tabName: Int, val fragmentClass: Class<out Fragment>) {
    MVI(R.string.tab_mvi, MviFragment::class.java),
    MVVM(R.string.tab_mvvm, MvvmFragment::class.java),
    MVVM_INTENT(R.string.tab_mvvm_intent, MvvmIntentFragment::class.java),
    COMPOSE(R.string.tab_compose, ComposeFragment::class.java)
}
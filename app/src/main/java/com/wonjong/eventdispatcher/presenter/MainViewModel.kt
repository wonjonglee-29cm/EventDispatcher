package com.wonjong.eventdispatcher.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjong.eventdispatcher.presenter.tabs.TabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Log.e(javaClass.simpleName, throwable.toString())
    }

    val tabs = flow<List<TabType>> {
        emit(TabType.values().toList())
    }.stateIn(scope, SharingStarted.Eagerly, emptyList())
}
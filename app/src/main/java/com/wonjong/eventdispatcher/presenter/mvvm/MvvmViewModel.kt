package com.wonjong.eventdispatcher.presenter.mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjong.eventdispatcher.domain.entity.PostEntity
import com.wonjong.eventdispatcher.domain.usecase.GetPosts
import com.wonjong.eventdispatcher.presenter.utils.LCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
@HiltViewModel
class MvvmViewModel @Inject constructor(
    private val getPosts: GetPosts,
) : ViewModel() {
    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Log.e(javaClass.simpleName, throwable.toString())
    }

    private val _events = MutableSharedFlow<MvvmEvents>()
    val events = _events.asSharedFlow()

    val posts = flow<LCE<List<PostEntity>>> {
        emit(LCE.Loading)
        getPosts.execute().onSuccess { posts ->
            emit(LCE.Content(posts))
        }.onFailure { throwable ->
            emit(LCE.Error(throwable))
        }
    }

    fun onItemClick(post: PostEntity) {
        scope.launch {
            _events.emit(MvvmEvents.ClickItem(post = post))
        }
    }

    sealed interface MvvmEvents {
        data class ClickItem(
            val post: PostEntity
        ) : MvvmEvents
    }
}
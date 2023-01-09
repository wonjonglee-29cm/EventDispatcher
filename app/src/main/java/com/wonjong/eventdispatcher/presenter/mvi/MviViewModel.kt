package com.wonjong.eventdispatcher.presenter.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjong.eventdispatcher.domain.entity.PostEntity
import com.wonjong.eventdispatcher.domain.usecase.GetPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
@HiltViewModel
class MviViewModel @Inject constructor(
    private val getPosts: GetPosts,
) : ViewModel() {
    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Log.e(javaClass.simpleName, throwable.toString())
    }

    val intent = Channel<MviIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MviUiState>(MviUiState.Idle)
    val state: StateFlow<MviUiState> = _state.asStateFlow()

    init {
        // intent to action
        scope.launch {
            intent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is MviIntent.FetchPosts -> {
                        fetchPosts()
                    }
                    is MviIntent.ClickPost -> {
                        // Handle your business logic :D
                    }
                }
            }
        }
    }

    private suspend fun fetchPosts() {
        _state.emit(MviUiState.Loading)
        getPosts.execute().reduce(_state)
    }

    sealed interface MviUiState {
        object Idle : MviUiState
        object Loading : MviUiState
        object Empty : MviUiState

        data class Contents(
            val data: List<PostEntity>
        ) : MviUiState

        data class Error(
            val message: String
        ) : MviUiState
    }

    sealed interface MviIntent {
        object FetchPosts : MviIntent

        data class ClickPost(
            val post: PostEntity
        ) : MviIntent
    }

    private suspend fun Result<List<PostEntity>>.reduce(state: MutableStateFlow<MviUiState>) = onSuccess {
        if (it.isEmpty()) {
            state.emit(MviUiState.Empty)
        } else {
            state.emit(MviUiState.Contents(it))
        }
    }.onFailure {
        state.emit(MviUiState.Error(it.toString()))
    }
}
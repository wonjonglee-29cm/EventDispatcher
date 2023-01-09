package com.wonjong.eventdispatcher.presenter.mvvm_intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.wonjong.eventdispatcher.databinding.FragmentMvvmIntentBinding
import com.wonjong.eventdispatcher.event.EventTracker
import com.wonjong.eventdispatcher.presenter.mvvm_intent.adapter.MvvmIntentAdapter
import com.wonjong.eventdispatcher.presenter.mvvm_intent.dispatcher.MvvmIntentEventDispatcher
import com.wonjong.eventdispatcher.presenter.mvvm_intent.dispatcher.events.PostEvents
import com.wonjong.eventdispatcher.presenter.utils.LCE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
@AndroidEntryPoint
class MvvmIntentFragment : Fragment() {
    private var _binding: FragmentMvvmIntentBinding? = null
    private val binding: FragmentMvvmIntentBinding get() = _binding!!

    private val viewModel: MvvmIntentViewModel by viewModels<MvvmIntentViewModel>()

    @Inject
    lateinit var eventTracker: EventTracker

    private val eventDispatcher by lazy {
        MvvmIntentEventDispatcher(
            viewModel = viewModel,
            eventTracker = eventTracker
        )
    }

    private val mvvmIntentAdapter: MvvmIntentAdapter by lazy {
        MvvmIntentAdapter(onItemClick = { postEntity ->
            eventDispatcher.dispatchEvent(
                PostEvents.ClickItem(
                    id = postEntity.id,
                    userId = postEntity.userId,
                    title = postEntity.title
                )
            )
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMvvmIntentBinding.inflate(inflater, container, false)
        setUpViews()
        collectFlows()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.list.adapter = null
        _binding = null
    }

    private fun setUpViews() {
        binding.list.adapter = mvvmIntentAdapter
    }

    private fun collectFlows() {
        viewModel.posts.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach { lce ->
            binding.loading.isVisible = lce is LCE.Loading
            binding.error.isVisible = lce is LCE.Error
            binding.list.isVisible = lce is LCE.Content
            mvvmIntentAdapter.submitList(lce.data ?: emptyList())
        }.launchIn(lifecycleScope)

        viewModel.actions.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach { action ->
            handleAction(action)
        }.launchIn(lifecycleScope)
    }

    private fun handleAction(state: MvvmIntentViewModel.MvvmIntentAction) = when (state) {
        is MvvmIntentViewModel.MvvmIntentAction.ShowToast -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
    }
}
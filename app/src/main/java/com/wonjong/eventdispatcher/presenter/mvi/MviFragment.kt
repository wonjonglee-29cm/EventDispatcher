package com.wonjong.eventdispatcher.presenter.mvi

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
import com.wonjong.eventdispatcher.R
import com.wonjong.eventdispatcher.databinding.FragmentMviBinding
import com.wonjong.eventdispatcher.event.EventTracker
import com.wonjong.eventdispatcher.event.code.EventTrackingCode
import com.wonjong.eventdispatcher.event.type.EventTrackingType
import com.wonjong.eventdispatcher.presenter.mvi.adapter.MviAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
@AndroidEntryPoint
class MviFragment : Fragment() {
    private var _binding: FragmentMviBinding? = null
    private val binding: FragmentMviBinding get() = _binding!!

    private val viewModel: MviViewModel by viewModels<MviViewModel>()

    @Inject
    lateinit var eventTracker: EventTracker

    private val mviAdapter: MviAdapter by lazy {
        MviAdapter(onItemClick = {
            lifecycleScope.launch {
                viewModel.intent.send(MviViewModel.MviIntent.ClickPost(it))
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                val params = JSONObject().apply {
                    put(EventTrackingCode.ID, it.id)
                    put(EventTrackingCode.USER_ID, it.userId)
                    put(EventTrackingCode.TITLE, it.title)
                }
                eventTracker.send(EventTrackingType.CLICK_ITEM, params)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMviBinding.inflate(inflater, container, false)
        setUpViews()
        handleState()

        lifecycleScope.launch {
            delay(1000L)
            viewModel.intent.send(MviViewModel.MviIntent.FetchPosts)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.list.adapter = null
        _binding = null
    }

    private fun setUpViews() {
        binding.list.adapter = mviAdapter
    }

    private fun handleState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach { state ->
            binding.notice.isVisible = state is MviViewModel.MviUiState.Idle || state is MviViewModel.MviUiState.Empty
            binding.loading.isVisible = state is MviViewModel.MviUiState.Loading
            binding.error.isVisible = state is MviViewModel.MviUiState.Error
            binding.list.isVisible = state is MviViewModel.MviUiState.Contents

            when (state) {
                is MviViewModel.MviUiState.Idle -> {
                    binding.notice.text = getString(R.string.idle)
                }
                is MviViewModel.MviUiState.Empty -> {
                    binding.notice.text = getString(R.string.empty)
                }
                is MviViewModel.MviUiState.Loading -> {
                    // Nothing
                }
                is MviViewModel.MviUiState.Contents -> {
                    mviAdapter.submitList(state.data)
                }
                is MviViewModel.MviUiState.Error -> {
                    Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(lifecycleScope)
    }
}
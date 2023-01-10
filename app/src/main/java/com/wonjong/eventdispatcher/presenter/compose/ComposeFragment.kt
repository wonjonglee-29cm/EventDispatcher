package com.wonjong.eventdispatcher.presenter.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wonjong.eventdispatcher.R
import com.wonjong.eventdispatcher.databinding.FragmentComposeBinding
import com.wonjong.eventdispatcher.domain.entity.PostEntity
import com.wonjong.eventdispatcher.event.EventTracker
import com.wonjong.eventdispatcher.presenter.compose.dispatcher.ComposeEventDispatcher
import com.wonjong.eventdispatcher.presenter.compose.dispatcher.events.PostEvents
import com.wonjong.eventdispatcher.presenter.compose.item.PostItem
import com.wonjong.eventdispatcher.presenter.utils.LCE
import com.wonjong.eventdispatcher.ui.component.progress.LargeLoading
import com.wonjong.eventdispatcher.ui.component.text.extension.textDp
import com.wonjong.eventdispatcher.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-09
 */
@AndroidEntryPoint
class ComposeFragment : Fragment() {
    private var _binding: FragmentComposeBinding? = null
    private val binding: FragmentComposeBinding get() = _binding!!

    private val viewModel: ComposeViewModel by viewModels<ComposeViewModel>()

    @Inject
    lateinit var eventTracker: EventTracker

    private val eventDispatcher by lazy {
        ComposeEventDispatcher(
            viewModel = viewModel,
            eventTracker = eventTracker
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentComposeBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeRoute(eventDispatcher = eventDispatcher)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ComposeRoute(
    viewModel: ComposeViewModel = hiltViewModel(),
    eventDispatcher: ComposeEventDispatcher? = null,
) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()
    val actions by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)

    HandleActions(action = actions)


    when (posts) {
        is LCE.Loading -> {
            Row(
                modifier = defaultBackgroundModifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LargeLoading()
            }
        }
        is LCE.Content -> {
            PostItemContent(posts = posts.data ?: return, onItemClick = { post ->
                eventDispatcher?.dispatchEvent(
                    PostEvents.ClickItem(
                        id = post.id,
                        userId = post.userId,
                        title = post.title
                    )
                )
            })
        }
        is LCE.Error -> {
            Row(
                modifier = defaultBackgroundModifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ErrorText()
            }
        }
    }
}

@Composable
private fun HandleActions(action: ComposeViewModel.ComposeAction?) = when (action) {
    is ComposeViewModel.ComposeAction.ShowToast -> {
        Toast.makeText(LocalContext.current, action.message, Toast.LENGTH_SHORT).show()
    }
    else -> {
        // Nothing
    }
}

@Composable
private fun PostItemContent(
    posts: List<PostEntity>,
    modifier: Modifier = Modifier,
    onItemClick: ((PostEntity) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
    ) {
        posts.forEach { postEntity: PostEntity ->
            item(key = postEntity.id) {
                PostItem(
                    post = postEntity,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
private fun ErrorText(
    text: String = stringResource(id = R.string.error_message),
    textColor: Color = Color.Red
) {
    Text(
        text = text,
        style = TextStyle(
            color = textColor,
            fontSize = 60.textDp
        ),
    )
}

private val defaultBackgroundModifier = Modifier
    .fillMaxHeight()
    .fillMaxWidth()
    .background(White)
package com.wonjong.eventdispatcher.presenter.compose.item

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wonjong.eventdispatcher.R
import com.wonjong.eventdispatcher.domain.entity.PostEntity
import com.wonjong.eventdispatcher.ui.annotation.DevicePreview
import com.wonjong.eventdispatcher.ui.component.image.SquareImage
import com.wonjong.eventdispatcher.ui.component.text.NumberBadge
import com.wonjong.eventdispatcher.ui.component.text.extension.textDp
import com.wonjong.eventdispatcher.ui.theme.Black
import com.wonjong.eventdispatcher.ui.theme.Gray
import com.wonjong.eventdispatcher.ui.theme.White

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-10
 */
@Composable
fun PostItem(
    post: PostEntity, modifier: Modifier = Modifier, onItemClick: ((PostEntity) -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                onItemClick?.invoke(post)
            }
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Box {
            SquareImage(
                data = ColorDrawable(post.colorInt), modifier = Modifier.align(Alignment.Center)
            )
            NumberBadge(
                number = post.id, modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            TitleText(
                text = post.title, modifier = modifier
            )
            Spacer(modifier = Modifier.height(4.dp))
            DescriptionText(
                text = post.body, modifier = modifier
            )
        }
    }
}

@Composable
private fun TitleText(
    text: String, modifier: Modifier
) = Text(
    text = text, modifier = modifier, color = Black, fontSize = 14.textDp, maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold
)

@Composable
private fun DescriptionText(
    text: String, modifier: Modifier, maxLines: Int = 3
) = Text(
    text = text,
    modifier = modifier,
    color = Gray,
    fontSize = 12.textDp,
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
)

@DevicePreview
@Composable
private fun PostItemPreview() {
    PostItem(
        post = PostEntity(
            id = 0, userId = 1, colorInt = android.graphics.Color.argb(255, 0, 0, 0), title = "Post Test Title", body = stringResource(id = R.string.lorem_ipsum)
        )
    )
}
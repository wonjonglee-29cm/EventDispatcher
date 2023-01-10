package com.wonjong.eventdispatcher.ui.component.image

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.wonjong.eventdispatcher.ui.annotation.DevicePreview
import com.wonjong.eventdispatcher.ui.theme.White

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-09
 */
private object SquareImageDefaults {
    val large = 120.dp
    val medium = 90.dp
    val small = 60.dp
}

@Composable
@SuppressLint("ModifierParameter")
fun SquareImage(
    data: Any? = null,
    backgroundColor: Color = Color.Gray,
    size: Dp = SquareImageDefaults.small,
    round: Dp = 0.dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .size(size = size)
            .background(backgroundColor)
            .clip(shape = RoundedCornerShape(CornerSize(round))),
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                PlaceHolderShimmerImage()
            }
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Error -> {
                PlaceHolderImage()
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
fun PlaceHolderShimmerImage(
    size: Dp = SquareImageDefaults.small,
    visible: Boolean = true
) {
    Box(
        modifier = Modifier
            .size(size)
            .placeholder(
                visible = visible,
                color = Color.Gray,
                shape = RoundedCornerShape(2.dp),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = White,
                ),
            )
    )
}

@Composable
fun PlaceHolderImage(
    size: Dp = SquareImageDefaults.small,
    visible: Boolean = true
) {
    Box(
        modifier = Modifier
            .size(size)
            .placeholder(
                visible = visible,
                color = Color.Gray,
                shape = RoundedCornerShape(2.dp),
            )
    )
}

@DevicePreview
@Composable
private fun SquareImagePreview() {
    Column {
        SquareImage(
            data = "https://picsum.photos/200",
            modifier = Modifier
        )
        PlaceHolderShimmerImage()
        PlaceHolderImage()
    }
}
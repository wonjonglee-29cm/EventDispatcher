package com.wonjong.eventdispatcher.ui.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wonjong.eventdispatcher.ui.annotation.DevicePreview
import com.wonjong.eventdispatcher.ui.component.text.extension.textDp
import com.wonjong.eventdispatcher.ui.theme.Black
import com.wonjong.eventdispatcher.ui.theme.White

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-10
 */
@Composable
fun NumberBadge(
    number: String,
    modifier: Modifier = Modifier
) = Text(
    text = number,
    modifier = modifier
        .background(Black)
        .width(20.dp)
        .height(20.dp)
        .wrapContentSize(Alignment.Center),
    color = White,
    fontSize = 10.textDp,
    maxLines = 1,
    textAlign = TextAlign.Center,
    letterSpacing = (-0.05).textDp,
    overflow = TextOverflow.Ellipsis
)

@Composable
fun NumberBadge(
    number: Int,
    modifier: Modifier = Modifier
) = NumberBadge(number = number.toString(), modifier = modifier)

@DevicePreview
@Composable
private fun NumberBadgePreview() {
    NumberBadge(number = 100)
}
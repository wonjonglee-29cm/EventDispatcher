package com.wonjong.eventdispatcher.ui.component.progress

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wonjong.eventdispatcher.ui.annotation.DevicePreview

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-09
 */

private object ProgressIndicatorDefaults {
    val sizeLarge = 32.dp to 2.dp
    val sizeMedium = 24.dp to 1.5.dp
    val sizeSmall = 16.dp to 1.dp
    val size = 48.dp to 4.dp
}

@Composable
fun LargeLoading(
    modifier: Modifier = Modifier,
    size: Dp = ProgressIndicatorDefaults.size.first,
    strokeWidth: Dp = ProgressIndicatorDefaults.size.second,
    color: Color = MaterialTheme.colorScheme.inversePrimary,
) {
    CircularProgressIndicator(modifier.size(size), color, strokeWidth)
}

@DevicePreview
@Composable
private fun LargeLoadingPreview() {
    LargeLoading()
}
package com.wonjong.eventdispatcher.ui.component.text.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-09
 */
fun Int.textDp(density: Density): TextUnit = with(density) {
    dp.toSp()
}

val Int.textDp: TextUnit
    @Composable get() = textDp(density = LocalDensity.current)

fun Double.textDp(density: Density): TextUnit = with(density) {
    dp.toSp()
}

val Double.textDp: TextUnit
    @Composable get() = textDp(density = LocalDensity.current)

fun Float.textDp(density: Density): TextUnit = with(density) {
    dp.toSp()
}

val Float.textDp: TextUnit
    @Composable get() = textDp(density = LocalDensity.current)
package com.example.p3re.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun rememberWindowInfo(): WindowInfo {

    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when{
            configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Small
            configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < 480 -> WindowInfo.WindowType.Small
            configuration.screenHeightDp < 700 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidth = configuration.screenHeightDp.dp,
        screenHeigh = configuration.screenHeightDp.dp
    )


}
data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeigh: Dp

    ){
    sealed class WindowType{
        object Small: WindowType()
        object Medium: WindowType()
        object Expanded: WindowType()
    }

}
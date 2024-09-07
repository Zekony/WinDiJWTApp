package com.zekony.windichat.ui.chat.ui.utility

import androidx.compose.ui.graphics.Color

fun decideChatCircleColor(username: String): Color {
    val colors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Cyan,
        Color.Green,
        Color.Magenta
    )
    val num = (username.length + colors.size) % colors.size
    return colors[num]
}

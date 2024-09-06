package com.zekony.windichat.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.ui.graphics.vector.ImageVector
import com.zekony.windichat.R
import com.zekony.windichat.ui.chat.CHAT_ROUTE
import com.zekony.windichat.ui.profile.PROFILE_ROUTE

enum class NavigationButtons(
    @StringRes val screenName: Int,
    val activeIcon: ImageVector,
    val notActiveIcon: ImageVector,
    val route: String
) {
    Profile(
        R.string.profile,
        Icons.Default.AccountBox,
        Icons.Outlined.AccountBox,
        PROFILE_ROUTE
    ),
    Chat(
        R.string.chat,
        Icons.AutoMirrored.Filled.Chat,
        Icons.AutoMirrored.Outlined.Chat,
        CHAT_ROUTE
    )
}
package com.zekony.windichat.ui.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zekony.windichat.ui.chat.mvi.ChatEvent
import com.zekony.windichat.ui.chat.mvi.ChatViewModel
import com.zekony.windichat.ui.chat.ui.ChatScreen
import com.zekony.windichat.ui.chat.ui.OneChatScreen
import org.orbitmvi.orbit.compose.collectAsState

const val CHAT_ROUTE = "chat"

fun NavGraphBuilder.chatEntry() {
    composable(CHAT_ROUTE) {
        val viewModel: ChatViewModel = hiltViewModel()
        val state by viewModel.collectAsState()

        BackHandler(state.chosenChat != null) {
            viewModel.dispatch(ChatEvent.ExitChat)
        }
        Scaffold(
            modifier = Modifier,
            contentWindowInsets = WindowInsets.navigationBars
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                if (state.chosenChat == null) {
                    ChatScreen(state, viewModel::dispatch)
                } else {
                    OneChatScreen(state, viewModel::dispatch)
                }
            }
        }
    }
}
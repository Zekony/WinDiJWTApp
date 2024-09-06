package com.zekony.windichat.ui.chat

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zekony.windichat.ui.chat.mvi.ChatSideEffect
import com.zekony.windichat.ui.chat.mvi.ChatViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

const val CHAT_ROUTE = "chat"

fun NavGraphBuilder.chatEntry(

) {
    composable(CHAT_ROUTE) {
        val viewModel: ChatViewModel = hiltViewModel()
        val state by viewModel.collectAsState()
        val snackbarHost = remember { SnackbarHostState() }
        val context = LocalContext.current

        Text(text = "chat screen")

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ChatSideEffect.NavigateBack -> TODO()
            }
        }
    }
}
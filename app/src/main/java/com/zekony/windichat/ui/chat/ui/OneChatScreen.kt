package com.zekony.windichat.ui.chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zekony.windichat.ui.chat.mvi.ChatEvent
import com.zekony.windichat.ui.chat.mvi.ChatState
import com.zekony.windichat.ui.chat.ui.composables.InputRow
import com.zekony.windichat.ui.chat.ui.composables.MessageContent

@Composable
fun OneChatScreen(state: ChatState, onEvent: (ChatEvent) -> Unit) {
    if (state.chosenChat != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                state.chosenChat.messages.forEach {
                    MessageContent(it, state.currentUser)
                }
            }
            InputRow(state.messageInput, onEvent)
        }
    }
}
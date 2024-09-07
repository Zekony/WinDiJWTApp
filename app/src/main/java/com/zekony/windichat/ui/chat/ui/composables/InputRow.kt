package com.zekony.windichat.ui.chat.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zekony.windichat.ui.authorization.ui.composables.PrimaryTextField
import com.zekony.windichat.ui.chat.mvi.ChatEvent

@Composable
fun InputRow(messageInput: String, onEvent: (ChatEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        PrimaryTextField(
            modifier = Modifier,
            text = messageInput,
            onInputEvent = { onEvent(ChatEvent.MessageInput(it)) },
            width = 0.85f
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onEvent(ChatEvent.SendMessage) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
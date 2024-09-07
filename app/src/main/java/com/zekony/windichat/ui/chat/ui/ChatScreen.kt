package com.zekony.windichat.ui.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zekony.windichat.domain.models.Chat
import com.zekony.windichat.ui.chat.mvi.ChatEvent
import com.zekony.windichat.ui.chat.mvi.ChatState
import com.zekony.windichat.ui.chat.ui.utility.decideChatCircleColor

@Composable
fun ChatScreen(state: ChatState, onEvent: (ChatEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemsIndexed(state.chatList) { index, chat ->
            ChatContent(chat) { onEvent(ChatEvent.ChooseChat(chat.id)) }
            if (index != state.chatList.size - 1) HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}

@Composable
fun ChatContent(chat: Chat, onChatClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(6.dp))
            .sizeIn(maxHeight = 60.dp)
            .clickable { onChatClick() },
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(60.dp)
                .drawBehind { drawCircle(decideChatCircleColor(chat.companionUser.name), radius = size.minDimension / 3.0f) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.companionUser.name.first().uppercase(),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically)
        ) {
            Text(text = chat.companionUser.name, style = MaterialTheme.typography.labelMedium)
            Text(
                text = chat.messages.maxBy { it.messageTime }.messageText,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
    }
}




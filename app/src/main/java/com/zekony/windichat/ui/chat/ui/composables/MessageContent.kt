package com.zekony.windichat.ui.chat.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zekony.windichat.domain.models.Message
import com.zekony.windichat.domain.models.User

@Composable
fun MessageContent(message: Message, currentUser: User?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.senderUser == currentUser) Arrangement.End else Arrangement.Start
    ) {
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(
                containerColor = if (message.senderUser == currentUser) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = message.messageText,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}
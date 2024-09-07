package com.zekony.windichat.ui.chat.mvi

import com.zekony.windichat.domain.models.Chat
import com.zekony.windichat.domain.models.Message
import com.zekony.windichat.domain.models.User

data class ChatState(
    val chatList: List<Chat> = listOf(
        exampleChat
    ),
    val chosenChat: Chat? = null,
    val messageInput: String = "",
    val currentUser: User? = user
)

val user = User(name = "me")
val sergeyUser = User(name = "Серега")

val exampleChat = Chat(
    id = 100,
    companionUser = sergeyUser,
    messages = listOf(
        Message(
            senderUser = sergeyUser,
            messageText = "Привет",
            messageTime = 1
        ),
        Message(
            senderUser = sergeyUser,
            messageText = "Ты уже закончил?",
            messageTime = 2
        ),
        Message(
            senderUser = user,
            messageText = "Заканчиваю",
            messageTime = 3
        ),
        Message(
            senderUser = user,
            messageText = "Скину как закончу",
            messageTime = 4
        ),
        Message(
            senderUser = sergeyUser,
            messageText = "Окей, давай жду",
            messageTime = 5
        )
    )
)
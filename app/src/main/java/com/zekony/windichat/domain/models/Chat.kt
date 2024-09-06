package com.zekony.windichat.domain.models

data class Chat(
    val id: Int = -1,
    val user: User = User(),
    val companionUser: User = User(),
    val messages: List<Message> = listOf(),
    val unreadMessagesAmount: Int = 0
)



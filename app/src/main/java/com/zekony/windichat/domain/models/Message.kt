package com.zekony.windichat.domain.models

data class Message(
    val id: Int = -1,
    val senderUser: User = User(),
    val messageText: String = "",
    val wasRead: Boolean = false,
    val messageTime: Long = 0L
)
package com.zekony.windichat.ui.chat.mvi

import com.zekony.windichat.domain.models.Chat

data class ChatState(
    val chatList: List<Chat> = listOf(),
    val chosenChat: Chat? = null,
    val input: String = ""
)
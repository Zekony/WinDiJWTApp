package com.zekony.windichat.ui.chat.mvi

sealed interface ChatEvent {
    data object ExitChat : ChatEvent
    data class ChooseChat(val chatId: Int): ChatEvent

    data class MessageInput(val input: String) : ChatEvent
    data object SendMessage : ChatEvent
}

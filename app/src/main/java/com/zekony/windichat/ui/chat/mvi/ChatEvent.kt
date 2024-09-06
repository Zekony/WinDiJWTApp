package com.zekony.windichat.ui.chat.mvi

sealed interface ChatEvent {
    data object OnNavigateBack : ChatEvent
}
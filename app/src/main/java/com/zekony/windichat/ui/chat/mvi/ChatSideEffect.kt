package com.zekony.windichat.ui.chat.mvi

sealed interface ChatSideEffect {
    data object NavigateBack : ChatSideEffect
}
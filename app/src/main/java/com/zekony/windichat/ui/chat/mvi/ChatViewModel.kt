package com.zekony.windichat.ui.chat.mvi

import com.zekony.windichat.utility.MviViewModel

class ChatViewModel(
) : MviViewModel<ChatState, ChatSideEffect, ChatEvent>(ChatState()) {

    override fun dispatch(event: ChatEvent) {
        when (event) {
            ChatEvent.OnNavigateBack -> {}
        }
    }
}
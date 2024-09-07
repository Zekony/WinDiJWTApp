package com.zekony.windichat.ui.chat.mvi

import com.zekony.windichat.domain.models.Message
import com.zekony.windichat.domain.models.User
import com.zekony.windichat.utility.MviViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class ChatViewModel(
) : MviViewModel<ChatState, ChatSideEffect, ChatEvent>(ChatState()) {

    override fun dispatch(event: ChatEvent) {
        when (event) {
            ChatEvent.ExitChat -> exitChat()

            is ChatEvent.ChooseChat -> chooseChat(event.chatId)

            is ChatEvent.MessageInput -> messageInput(event.input)
            ChatEvent.SendMessage -> sendMessage()
        }
    }

    private fun exitChat() = intent {
        reduce { state.copy(chosenChat = null) }
    }

    private fun sendMessage() = intent {
        state.chosenChat?.let { chat ->
            val newMessage = Message(
                senderUser = state.currentUser ?: User(),
                messageText = state.messageInput,
                messageTime = chat.messages.maxBy { it.messageTime }.messageTime.inc()
            )
            reduce {
                state.copy(
                    chosenChat = chat.copy(
                        messages = chat.messages.toMutableList().apply {
                            this.add(newMessage)
                        }),
                    messageInput = ""
                )
            }
        }
    }

    @OptIn(OrbitExperimental::class)
    private fun messageInput(input: String) = blockingIntent {
        reduce { state.copy(messageInput = input) }
    }

    private fun chooseChat(chatId: Int) = intent {
        reduce { state.copy(chosenChat = state.chatList.first { it.id == chatId }) }
    }
}
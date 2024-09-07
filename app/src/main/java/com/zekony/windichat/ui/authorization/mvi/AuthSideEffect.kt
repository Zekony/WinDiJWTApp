package com.zekony.windichat.ui.authorization.mvi

sealed interface AuthSideEffect {
    data object NavigateProfileScreen: AuthSideEffect

    data class PostErrorMessage(val message: String?): AuthSideEffect
}
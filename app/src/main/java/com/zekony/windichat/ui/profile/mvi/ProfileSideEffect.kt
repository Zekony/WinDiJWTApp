package com.zekony.windichat.ui.profile.mvi

sealed interface ProfileSideEffect {
    data object Logout : ProfileSideEffect
    data object ChooseImage : ProfileSideEffect

    data class PostErrorMessage(val message: String?) : ProfileSideEffect
}
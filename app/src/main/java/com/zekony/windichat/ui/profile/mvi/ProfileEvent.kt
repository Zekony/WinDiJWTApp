package com.zekony.windichat.ui.profile.mvi

import com.zekony.windichat.data.network.models.requests.updateUserRequest.Avatar

sealed interface ProfileEvent {
    data object Initialize : ProfileEvent

    data class NameInput(val input: String) : ProfileEvent
    data class CityInput(val input: String) : ProfileEvent
    data class BirthdayInput(val input: String) : ProfileEvent

    data object ChooseImage : ProfileEvent
    data class SaveImage(val avatar: Avatar) : ProfileEvent

    data object SaveChanges : ProfileEvent

    data class ChangeInfoType(val info: ChangeInfo) : ProfileEvent
}
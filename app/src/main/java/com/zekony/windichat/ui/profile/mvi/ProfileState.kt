package com.zekony.windichat.ui.profile.mvi

import com.zekony.windichat.domain.models.User

data class ProfileState(
    val downloadState: DownloadState = DownloadState.Idle,
    val changeInfoState: ChangeInfo = ChangeInfo.Disabled,
    val nameInput: String = "",
    val cityInput: String = "",
    val birthdayInput: String = "",
    val currentUser: User? = null
)

enum class DownloadState {
    Downloading, Idle
}
enum class ChangeInfo {
    Active, Disabled
}
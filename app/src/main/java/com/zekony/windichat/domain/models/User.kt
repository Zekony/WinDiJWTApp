package com.zekony.windichat.domain.models

import com.zekony.windichat.data.network.models.requests.updateUserRequest.Avatar
import com.zekony.windichat.data.network.models.responses.getUserResponse.Avatars
import com.zekony.windichat.data.network.models.responses.getUserResponse.GetUserResponse
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val phone: String = "",
    val username: String = "",
    val name: String = "",
    val avatar: Avatar = Avatar(),
    val avatars: Avatars = Avatars(),
    val birthday: String = "",
    val city: String = ""
)

fun GetUserResponse.toUser(): User =
    with(this.profileData) {
        User(
            phone ?: "",
            username ?: "",
            name ?: "",
            avatar = Avatar(),
            avatars ?: Avatars(),
            birthday ?: "",
            city ?: ""
        )
    }


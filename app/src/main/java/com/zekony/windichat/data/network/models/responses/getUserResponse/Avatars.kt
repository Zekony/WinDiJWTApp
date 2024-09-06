package com.zekony.windichat.data.network.models.responses.getUserResponse

import kotlinx.serialization.Serializable

@Serializable
data class Avatars(
    val avatar: String? = "",
    val bigAvatar: String? = "",
    val miniAvatar: String? = ""
)
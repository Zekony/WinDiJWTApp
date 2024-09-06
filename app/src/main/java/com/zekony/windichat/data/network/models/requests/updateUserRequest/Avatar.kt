package com.zekony.windichat.data.network.models.requests.updateUserRequest

import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    val base_64: String? = "",
    val filename: String? = ""
)
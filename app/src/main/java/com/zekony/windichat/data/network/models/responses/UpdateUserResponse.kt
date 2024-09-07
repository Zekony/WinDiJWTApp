package com.zekony.windichat.data.network.models.responses

import com.zekony.windichat.data.network.models.responses.getUserResponse.Avatars


data class UpdateUserResponse(
    val avatars: Avatars?
)
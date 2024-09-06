package com.zekony.windichat.data.network.models.responses

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("user_id")
    val userId: Int
)

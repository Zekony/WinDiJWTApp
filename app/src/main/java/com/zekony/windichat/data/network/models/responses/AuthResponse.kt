package com.zekony.windichat.data.network.models.responses

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("is_user_exists")
    val userExists: Boolean,
    @SerializedName("user_id")
    val userId: Int?
)
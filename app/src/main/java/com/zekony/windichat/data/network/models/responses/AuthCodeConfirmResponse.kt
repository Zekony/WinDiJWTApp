package com.zekony.windichat.data.network.models.responses

import com.google.gson.annotations.SerializedName

data class AuthCodeConfirmResponse(
    @SerializedName("is_success")
    val isSuccess: Boolean
)

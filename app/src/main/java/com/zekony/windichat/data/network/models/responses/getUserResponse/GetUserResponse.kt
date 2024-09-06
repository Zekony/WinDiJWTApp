package com.zekony.windichat.data.network.models.responses.getUserResponse

import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("profile_data")
    val profileData: ProfileData
)
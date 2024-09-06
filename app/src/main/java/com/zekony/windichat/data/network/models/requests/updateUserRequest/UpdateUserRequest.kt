package com.zekony.windichat.data.network.models.requests.updateUserRequest

data class UpdateUserRequest(
    val name: String?,
    val username: String?,
    val avatar: Avatar?,
    val birthday: String?,
    val city: String?,
    val instagram: String? = "",
    val status: String? = "",
    val vk: String? = ""
)
package com.zekony.windichat.data.network.models.responses.getUserResponse

data class ProfileData(
    val phone: String?,
    val username: String?,
    val name: String?,
    val avatar: String?,
    val avatars: Avatars?,
    val birthday: String?,
    val city: String?,
    val completed_task: Int?,
    val created: String?,
    val id: Int?,
    val instagram: String?,
    val last: String?,
    val online: Boolean?,
    val status: String?,
    val vk: String?
)
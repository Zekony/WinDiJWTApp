package com.zekony.windichat.data.network.models.requests

data class RegisterRequest(
    val phone: String,
    val name: String,
    val username: String
)

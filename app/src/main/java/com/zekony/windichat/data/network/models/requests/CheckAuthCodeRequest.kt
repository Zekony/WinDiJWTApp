package com.zekony.windichat.data.network.models.requests

data class CheckAuthCodeRequest(
    val phone: String,
    val code: String
)

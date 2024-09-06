package com.zekony.windichat.domain.repositories

import com.zekony.windichat.data.network.models.responses.AuthCodeConfirmResponse
import com.zekony.windichat.data.network.models.responses.AuthResponse
import com.zekony.windichat.data.network.models.responses.RegistrationResponse
import com.zekony.windichat.utility.helperClasses.ApiResponse

interface AuthRepository {

    suspend fun register(
        phone: String,
        name: String,
        username: String,
    ): ApiResponse<RegistrationResponse>

    suspend fun checkAuthCode(
        phoneNumber: String,
        code: String
    ): ApiResponse<AuthResponse>

    suspend fun sendAuthCode(
        phoneNumber: String,
    ): ApiResponse<AuthCodeConfirmResponse>

    suspend fun refreshAccessToken(refreshToken: String): ApiResponse<RegistrationResponse>
}

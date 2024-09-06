package com.zekony.windichat.data.network.apiServices

import com.zekony.windichat.data.network.models.requests.CheckAuthCodeRequest
import com.zekony.windichat.data.network.models.requests.RegisterRequest
import com.zekony.windichat.data.network.models.requests.SendAuthCodeRequest
import com.zekony.windichat.data.network.models.responses.AuthCodeConfirmResponse
import com.zekony.windichat.data.network.models.responses.AuthResponse
import com.zekony.windichat.data.network.models.responses.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body body: SendAuthCodeRequest,
    ): Response<AuthCodeConfirmResponse>

    @POST("/api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body body: CheckAuthCodeRequest,
    ): Response<AuthResponse>

    @POST("/api/v1/users/register/")
    suspend fun register(
        @Body body: RegisterRequest,
    ): Response<RegistrationResponse>

    @GET("/api/v1/users/refresh-token/")
    suspend fun getNewAccessToken(
        @Header("Authorization") token: String,
    ): Response<RegistrationResponse>
}


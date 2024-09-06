package com.zekony.windichat.data.network.apiRepositories

import android.util.Log
import com.zekony.windichat.data.network.apiServices.AuthApiService
import com.zekony.windichat.data.network.models.requests.CheckAuthCodeRequest
import com.zekony.windichat.data.network.models.requests.RegisterRequest
import com.zekony.windichat.data.network.models.requests.SendAuthCodeRequest
import com.zekony.windichat.domain.repositories.AuthRepository
import com.zekony.windichat.utility.helperClasses.apiResponseHandler
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
): AuthRepository {

    override suspend fun register(
        phone: String,
        name: String,
        username: String,
    ) = apiResponseHandler {
        authApiService.register(RegisterRequest(phone, name, username))
    }

    override suspend fun checkAuthCode(
        phoneNumber: String,
        code: String
    ) = apiResponseHandler {
        Log.d("Zenais", "AuthRepo: check number $phoneNumber code $code")
        authApiService.checkAuthCode(CheckAuthCodeRequest(phoneNumber, code))
    }

    override suspend fun sendAuthCode(
        phoneNumber: String,
    ) = apiResponseHandler {
        Log.d("Zenais", "AuthRepo: check number $phoneNumber length ${phoneNumber.length}")
        authApiService.sendAuthCode(body = SendAuthCodeRequest(phoneNumber))
    }

    override suspend fun refreshAccessToken(refreshToken: String) = apiResponseHandler {
        authApiService.getNewAccessToken(refreshToken)
    }
}
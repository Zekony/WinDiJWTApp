package com.zekony.windichat.data.network.interceptor

import com.zekony.windichat.data.localStorage.TokenManager
import com.zekony.windichat.data.network.apiServices.AuthApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val apiService: AuthApiService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken().first()
        }
        return runBlocking {
            val newToken = apiService.getNewAccessToken(token.toString())
            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenManager.deleteAccessToken()
            }

            newToken.body()?.let {
                tokenManager.saveAccessToken(it.accessToken)
                tokenManager.saveRefreshToken(it.refreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }
}
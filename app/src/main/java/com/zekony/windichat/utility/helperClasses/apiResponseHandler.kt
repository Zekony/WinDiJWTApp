package com.zekony.windichat.utility.helperClasses

import com.google.gson.Gson
import com.zekony.windichat.data.network.models.responses.ErrorResponse
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

suspend fun <T> apiResponseHandler(call: suspend () -> Response<T>): ApiResponse<T> {

    return withTimeoutOrNull(10000L) {
        val response = call()
        try {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    ApiResponse.Success(data)
                }
            } else {
                response.errorBody()?.let { error ->
                    error.close()
                    val parsedError: ErrorResponse = Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                   ApiResponse.Failure(parsedError.detail.firstOrNull()?.msg, response.code())
                }
            }
        } catch (e: Exception) {
            ApiResponse.Failure(e.message ?: e.toString(), 400)
        }
    } ?: ApiResponse.Failure("Timeout! Please try again.", 408)
}
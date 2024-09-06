package com.zekony.windichat.data.network.apiServices

import com.zekony.windichat.data.network.models.requests.updateUserRequest.UpdateUserRequest
import com.zekony.windichat.data.network.models.responses.UpdateUserResponse
import com.zekony.windichat.data.network.models.responses.getUserResponse.GetUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApiService {
    @GET("/api/v1/users/me/")
    suspend fun getUser(): Response<GetUserResponse>

    @PUT("/api/v1/users/me/")
    suspend fun updateUser(
        @Body body: UpdateUserRequest,
    ): Response<UpdateUserResponse>
}

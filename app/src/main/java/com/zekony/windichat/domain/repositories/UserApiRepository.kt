package com.zekony.windichat.domain.repositories

import com.zekony.windichat.data.network.models.responses.UpdateUserResponse
import com.zekony.windichat.data.network.models.responses.getUserResponse.GetUserResponse
import com.zekony.windichat.domain.models.User
import com.zekony.windichat.utility.helperClasses.ApiResponse

interface UserApiRepository {
    suspend fun getUser() : ApiResponse<GetUserResponse>

    suspend fun updateUser(
        user: User,
    ) : ApiResponse<UpdateUserResponse>
}
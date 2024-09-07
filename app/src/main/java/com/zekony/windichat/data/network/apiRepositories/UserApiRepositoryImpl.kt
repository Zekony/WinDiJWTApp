package com.zekony.windichat.data.network.apiRepositories

import com.zekony.windichat.data.network.apiServices.UserApiService
import com.zekony.windichat.data.network.models.requests.updateUserRequest.UpdateUserRequest
import com.zekony.windichat.domain.models.User
import com.zekony.windichat.domain.repositories.UserApiRepository
import com.zekony.windichat.utility.helperClasses.apiResponseHandler

class UserApiRepositoryImpl(
    private val api: UserApiService
): UserApiRepository {

    override suspend fun getUser() = apiResponseHandler {
        api.getUser()
    }

    override suspend fun updateUser(
        user: User,
    ) = apiResponseHandler {
        with(user) {
            api.updateUser(
                UpdateUserRequest(
                    name, username, avatar, birthday, city
                )
            )
        }
    }
}
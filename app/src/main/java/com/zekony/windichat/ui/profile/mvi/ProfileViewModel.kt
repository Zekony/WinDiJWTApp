package com.zekony.windichat.ui.profile.mvi

import android.util.Log
import com.zekony.windichat.data.localStorage.TokenManager
import com.zekony.windichat.data.localStorage.UserDatastore
import com.zekony.windichat.data.network.models.requests.updateUserRequest.Avatar
import com.zekony.windichat.domain.models.toUser
import com.zekony.windichat.domain.repositories.UserApiRepository
import com.zekony.windichat.utility.MviViewModel
import com.zekony.windichat.utility.helperClasses.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val userDatastore: UserDatastore,
    private val userApiRepository: UserApiRepository
) : MviViewModel<ProfileState, ProfileSideEffect, ProfileEvent>(ProfileState()) {

    init {
        dispatch(ProfileEvent.Initialize)
    }

    override fun dispatch(event: ProfileEvent) {
        when (event) {
            ProfileEvent.Initialize -> initialize()

            is ProfileEvent.BirthdayInput -> birthdayInput(event.input)
            is ProfileEvent.CityInput -> cityInput(event.input)
            is ProfileEvent.NameInput -> nameInput(event.input)

            ProfileEvent.ChooseImage -> chooseImage()
            is ProfileEvent.SaveImage -> saveImage(event.avatar)

            is ProfileEvent.ChangeInfoType -> changeInfo(event.info)
            ProfileEvent.SaveChanges -> saveChanges()
        }
    }

    private fun initialize() = intent {
        collectCurrentUser(state)
        collectActiveToken()
    }

    private suspend fun SimpleSyntax<ProfileState, ProfileSideEffect>.collectCurrentUser(state: ProfileState) {
        userDatastore.loadUser().distinctUntilChanged().collect { currentUser ->
            if (currentUser == null) {
                reduce { state.copy(downloadState = DownloadState.Downloading) }
                val apiUserResponse = userApiRepository.getUser()
                processApiResponse(apiUserResponse) {
                    Log.d("Zenais", "ProfileViewModel: collecting api user: user: ${it.data}")
                    reduce { state.copy(currentUser = it.data.toUser(), downloadState = DownloadState.Idle) }
                    userDatastore.saveUser(it.data.toUser())
                }
            } else {
                reduce { state.copy(currentUser = currentUser, downloadState = DownloadState.Idle) }
            }
            Log.d("Zenais", "ProfileViewModel: collected datastore user: ${currentUser?.name}")
        }
    }

    private suspend fun SimpleSyntax<ProfileState, ProfileSideEffect>.collectActiveToken() {
        val activeToken = tokenManager.getAccessToken().first()
        Log.d("Zenais", "ProfileViewModel: collecting token: $activeToken")
        if (activeToken.isNullOrEmpty()) postSideEffect(ProfileSideEffect.Logout)
    }

    private fun saveChanges() = intent {
        state.currentUser?.let { user ->
            val newUser = user.copy(
                name = state.nameInput.ifEmpty { user.name },
                city = state.cityInput.ifEmpty { user.city },
                birthday = state.birthdayInput.ifEmpty { user.birthday }
            )
            if (user != newUser) {
                val response = userApiRepository.updateUser(newUser)
                Log.d("Zenais", "response for saving changed User: $response")
                processApiResponse(response) {
                    userDatastore.saveUser(newUser)
                    dispatch(ProfileEvent.ChangeInfoType(ChangeInfo.Disabled))
                }
            }
        }
    }

    private fun chooseImage() = intent {
        postSideEffect(ProfileSideEffect.ChooseImage)
    }

    private fun saveImage(avatar: Avatar) = intent {
        state.currentUser?.let {
            val newUser =  it.copy(avatar = avatar)
            val response = userApiRepository.updateUser(newUser)
            if (response is ApiResponse.Success) userDatastore.saveUser(newUser)
        }
    }

    private fun changeInfo(info: ChangeInfo) = intent {
        reduce { state.copy(changeInfoState = info) }
    }

    @OptIn(OrbitExperimental::class)
    private fun nameInput(input: String) = blockingIntent {
        if (input.length <= 24) reduce { state.copy(nameInput = input) }
    }

    @OptIn(OrbitExperimental::class)
    private fun cityInput(input: String) = blockingIntent {
        if (input.length <= 24) reduce { state.copy(cityInput = input) }
    }

    @OptIn(OrbitExperimental::class)
    private fun birthdayInput(input: String) = blockingIntent {
        if (input.length <= 10) reduce { state.copy(birthdayInput = input) }
    }

    private suspend fun <T> SimpleSyntax<ProfileState, ProfileSideEffect>.processApiResponse(
        response: ApiResponse<T>,
        processSuccess: suspend (ApiResponse.Success<T>) -> Unit
    ) {
        when (response) {
            is ApiResponse.Failure -> {
                postSideEffect(ProfileSideEffect.PostErrorMessage(response.errorMessage))
                reduce { state.copy(downloadState = DownloadState.Idle) }
            }
            is ApiResponse.Success -> {
                processSuccess(response)
            }
        }
    }
}
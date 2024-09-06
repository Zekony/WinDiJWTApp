package com.zekony.windichat.ui.authorization.mvi

import android.util.Log
import com.zekony.windichat.data.localStorage.TokenManager
import com.zekony.windichat.data.localStorage.UserDatastore
import com.zekony.windichat.domain.models.User
import com.zekony.windichat.domain.repositories.AuthRepository
import com.zekony.windichat.ui.authorization.ui.composables.countryPicker.CountryDetails
import com.zekony.windichat.utility.MviViewModel
import com.zekony.windichat.utility.helperClasses.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDatastore: UserDatastore,
    private val tokenManager: TokenManager
) : MviViewModel<AuthState, AuthSideEffect, AuthEvent>(AuthState()) {

    override fun dispatch(event: AuthEvent) {
        when (event) {
            AuthEvent.Authenticate -> authenticate()
            AuthEvent.SendCode -> sendCode()
            AuthEvent.Register -> register()

            is AuthEvent.ChooseCountry -> chooseCountryCode(event.countryDetails)
            is AuthEvent.OpenCountryCodeDialog -> openCountryCodeDialog(event.open)
            is AuthEvent.PhoneNumberInput -> phoneNumberInput(event.input)

            is AuthEvent.ConfCodeInput -> confCodeInput(event.input)

            is AuthEvent.NameInput -> nameInput(event.input)
            is AuthEvent.UserNameInput -> userNameInput(event.input)

        }
    }


    private fun register() = intent {
        val response = authRepository.register(
            state.chosenCountry?.countryPhoneNumberCode!! + state.phoneInput,
            state.nameInput,
            state.usernameInput
        )
        processApiResponse(response) {
            Log.d("Zenais", "got response for register: response: $response")
            with(it.data) {
                saveTokens(refreshToken, accessToken)
            }
            saveUserLocally()
            postSideEffect(AuthSideEffect.NavigateProfileScreen)
        }
    }

    private fun authenticate() = intent {
        Log.d("Zenais", "authenticate called")
        val response =
            authRepository.sendAuthCode(state.chosenCountry?.countryPhoneNumberCode!! + state.phoneInput)
        processApiResponse(response) {
            Log.d("Zenais", "got response for first call: response: $response")
            if (it.data.isSuccess) {
                reduce { state.copy(confirmationDialogIsOn = true) }
            } else {
                postSideEffect(AuthSideEffect.PostErrorMessage(null))
            }
        }
    }

    private fun sendCode() = intent {
        val response = authRepository.checkAuthCode(
            state.chosenCountry?.countryPhoneNumberCode!! + state.phoneInput,
            state.confirmationCodeInput
        )
        processApiResponse(response) { success ->
            Log.d("Zenais", "got response for sending conf code: response: $success")
            with(success.data) {
                if (!accessToken.isNullOrEmpty() && !refreshToken.isNullOrEmpty()) {
                    saveTokens(refreshToken, accessToken)
                }
            }
            if (success.data.userExists) {
                delay(200) //токен не успевает сохраниться до того как на экране Профиля пойдет запрос на юзера
                postSideEffect(AuthSideEffect.NavigateProfileScreen)
            } else {
                reduce { state.copy(isRegistered = UserRegistrationState.HaveNumber) }
            }
        }
    }

    private suspend fun SimpleSyntax<AuthState, AuthSideEffect>.saveUserLocally() {
        userDatastore.saveUser(
            User(
                phone = state.chosenCountry?.countryPhoneNumberCode!! + state.phoneInput,
                name = state.nameInput,
                username = state.usernameInput
            )
        )
    }



    private fun saveTokens(refreshToken: String, accessToken: String) = intent {
        tokenManager.saveAccessToken(accessToken)
        tokenManager.saveRefreshToken(refreshToken)
    }

    @OptIn(OrbitExperimental::class)
    private fun confCodeInput(input: String) = blockingIntent {
        if (input.length < 6) reduce { state.copy(confirmationCodeInput = input) }
    }

    @OptIn(OrbitExperimental::class)
    private fun phoneNumberInput(input: String) = blockingIntent {
        if (input.all { it.isDigit() } && input.length <= 10) reduce { state.copy(phoneInput = input) }
    }

    private fun openCountryCodeDialog(open: Boolean) = intent {
        reduce { state.copy(countryCodeDialogIsOn = open) }
    }

    private fun chooseCountryCode(countryDetails: CountryDetails) = intent {
        reduce { state.copy(chosenCountry = countryDetails, countryCodeDialogIsOn = false) }
    }


    @OptIn(OrbitExperimental::class)
    private fun userNameInput(input: String) = blockingIntent {
        if (validateUsernameInput(input)) reduce { state.copy(usernameInput = input) }
    }

    private fun validateUsernameInput(input: String): Boolean {
        if (input.length > 26) {
            return false
        }
        val regex = Regex("[A-Za-z]")
        var underscoreCount = 0
        var digitCount = 0
        for (char in input) {
            when {
                char.toString().matches(regex) -> continue
                char == '_' -> {
                    underscoreCount++
                    if (underscoreCount > 2) {
                        return false
                    }
                }

                char.isDigit() -> {
                    digitCount++
                    if (digitCount > 10) {
                        return false
                    }
                }

                else -> return false // Не подходит ни к одному условию
            }
        }
        return true
    }


    @OptIn(OrbitExperimental::class)
    private fun nameInput(input: String) = blockingIntent {
        if (input.length <= 16) reduce { state.copy(nameInput = input) }
    }

    private suspend fun <T> SimpleSyntax<AuthState, AuthSideEffect>.processApiResponse(
        response: ApiResponse<T>,
        processSuccess: suspend (ApiResponse.Success<T>) -> Unit
    ) {
        when (response) {
            is ApiResponse.Failure -> {
                postSideEffect(AuthSideEffect.PostErrorMessage(response.errorMessage))
            }

            is ApiResponse.Success -> {
                processSuccess(response)
            }
        }
    }
}
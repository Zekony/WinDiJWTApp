package com.zekony.windichat.ui.authorization.mvi

import com.zekony.windichat.ui.authorization.ui.composables.countryPicker.CountryDetails

data class AuthState(
    val isRegistered: UserRegistrationState = UserRegistrationState.Authorization,
    val phoneInput: String = "",
    val chosenCountry: CountryDetails? = null,

    val nameInput: String = "",
    val usernameInput: String = "",

    val confirmationDialogIsOn: Boolean = false,
    val confirmationCodeInput: String = "",

    val countryCodeDialogIsOn: Boolean = false
)

enum class UserRegistrationState {
    Registration, Authorization
}


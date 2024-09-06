package com.zekony.windichat.ui.authorization.mvi

import com.zekony.windichat.ui.authorization.ui.composables.countryPicker.CountryDetails

data class AuthState(
    val isRegistered: UserRegistrationState = UserRegistrationState.FirstTime,
    val phoneInput: String = "9219219211",
    val chosenCountry: CountryDetails? = null,

    val nameInput: String = "",
    val usernameInput: String = "",
    val inputError: InputError? = null,

    val confirmationDialogIsOn: Boolean = false,
    val confirmationCodeInput: String = "133337",

    val countryCodeDialogIsOn: Boolean = false
)

enum class UserRegistrationState {
    FirstTime, HaveNumber, MakingRequest
}

enum class InputError {
    PasswordLength, PasswordShouldContainSymbols, EmailShouldContain, NameLength
}

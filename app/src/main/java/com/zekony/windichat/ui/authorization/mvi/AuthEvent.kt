package com.zekony.windichat.ui.authorization.mvi

import com.zekony.windichat.ui.authorization.ui.composables.countryPicker.CountryDetails

sealed interface AuthEvent {
    data class PhoneNumberInput(val input: String) : AuthEvent
    data class OpenCountryCodeDialog(val open: Boolean) : AuthEvent
    data class ChooseCountry(val countryDetails: CountryDetails) : AuthEvent

    data class ConfCodeInput(val input: String) : AuthEvent

    data class NameInput(val input: String): AuthEvent
    data class UserNameInput(val input: String): AuthEvent

    data object Initialize : AuthEvent
    data object Authenticate : AuthEvent
    data object SendCode : AuthEvent
    data object Register : AuthEvent

}
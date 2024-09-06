package com.zekony.windichat.ui.authorization.ui.composables.countryPicker

import androidx.annotation.DrawableRes

data class CountryDetails(
    var countryCode: String,
    val countryPhoneNumberCode: String,
    val countryName: String,
    @DrawableRes val countryFlag: Int
)
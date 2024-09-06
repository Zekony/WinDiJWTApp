package com.zekony.windichat.ui.authorization.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zekony.windichat.ui.authorization.mvi.AuthEvent
import com.zekony.windichat.ui.authorization.mvi.AuthState
import com.zekony.windichat.ui.authorization.ui.composables.NumberConfirmationDialog
import com.zekony.windichat.ui.authorization.ui.composables.PhoneNumberTextField
import com.zekony.windichat.ui.authorization.ui.composables.countryPicker.CountryCodeDialog
import com.zekony.windichat.ui.authorization.ui.composables.countryPicker.getAllCountries
import com.zekony.windichat.utility.composables.PrimaryButton

@Composable
fun AuthorizationScreen(state: AuthState, onEvent: (AuthEvent) -> Unit) {

    val context = LocalContext.current
    val countryList = getAllCountries(context)
    LaunchedEffect(Unit) {
        if (state.chosenCountry == null) {
            val systemCountryCode = context.resources.configuration.locales[0].country
            val defaultCountry =
                countryList.first { it.countryCode == systemCountryCode.lowercase() }
            onEvent(AuthEvent.ChooseCountry(defaultCountry))
        }
    }
    if (state.countryCodeDialogIsOn) CountryCodeDialog(onEvent, countryList)
    if (state.confirmationDialogIsOn) NumberConfirmationDialog(state.confirmationCodeInput, onEvent)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Авторизация", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Номер телефона", style = MaterialTheme.typography.titleMedium)
        PhoneNumberTextField(state, onEvent)
        PrimaryButton(
            onClick = {  
                onEvent(AuthEvent.Authenticate)
                      },
            buttonText = "Войти",
            enabled = state.phoneInput.length == 10
        )
    }
}




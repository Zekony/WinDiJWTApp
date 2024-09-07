package com.zekony.windichat.ui.authorization.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zekony.windichat.R
import com.zekony.windichat.ui.authorization.mvi.AuthEvent
import com.zekony.windichat.ui.authorization.mvi.AuthState
import com.zekony.windichat.ui.authorization.ui.composables.PrimaryTextField

@Composable
fun RegistrationScreen(state: AuthState, onEvent: (AuthEvent) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = stringResource(id = R.string.number))
        PrimaryTextField(
            text = state.chosenCountry?.countryPhoneNumberCode!! + state.phoneInput,
            onInputEvent = {}, enabled = false
        )

        Text(text = stringResource(id = R.string.name))
        PrimaryTextField(
            text = state.nameInput,
            onInputEvent = { onEvent(AuthEvent.NameInput(it)) },
            placeholder = stringResource(R.string.name_example)
        )

        Text(text = stringResource(R.string.username))
        PrimaryTextField(
            text = state.usernameInput,
            onInputEvent = { onEvent(AuthEvent.UserNameInput(it)) },
            placeholder = stringResource(R.string.username_example)
        )

        Button(
            onClick = { onEvent(AuthEvent.Register) },
            enabled = state.nameInput.isNotEmpty() && state.usernameInput.isNotEmpty(),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = stringResource(R.string.register), style = MaterialTheme.typography.labelMedium)
        }
    }
}
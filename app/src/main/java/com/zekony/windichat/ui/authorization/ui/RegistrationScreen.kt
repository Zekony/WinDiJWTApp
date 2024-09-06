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
import androidx.compose.ui.unit.dp
import com.zekony.windichat.ui.authorization.mvi.AuthEvent
import com.zekony.windichat.ui.authorization.mvi.AuthState
import com.zekony.windichat.ui.authorization.ui.composables.AuthTextField

@Composable
fun RegistrationScreen(state: AuthState, onEvent: (AuthEvent) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Номер телефона")
        AuthTextField(
            text = state.chosenCountry?.countryPhoneNumberCode!! + state.phoneInput,
            onInputEvent = {}, enabled = false
        )

        Text(text = "Имя")
        AuthTextField(
            text = state.nameInput,
            onInputEvent = { onEvent(AuthEvent.NameInput(it)) },
            placeholder = "Евгений"
        )

        Text(text = "Юзернейм")
        AuthTextField(
            text = state.usernameInput,
            onInputEvent = { onEvent(AuthEvent.UserNameInput(it)) },
            placeholder = "Username_999_Username"
        )

        Button(
            onClick = { onEvent(AuthEvent.Register) },
            enabled = state.nameInput.isNotEmpty() && state.usernameInput.isNotEmpty(),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = "Зарегистрироваться", style = MaterialTheme.typography.labelMedium)
        }
    }
}
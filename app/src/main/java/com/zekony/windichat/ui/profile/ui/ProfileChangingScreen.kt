package com.zekony.windichat.ui.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BabyChangingStation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.zekony.windichat.ui.authorization.ui.composables.AuthTextField
import com.zekony.windichat.ui.profile.mvi.ProfileEvent
import com.zekony.windichat.ui.profile.mvi.ProfileState
import com.zekony.windichat.utility.composables.PrimaryButton

@Composable
fun ProfileChangingScreen(state: ProfileState, onEvent: (ProfileEvent) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.currentUser?.let { user ->
            Image(
                imageVector = Icons.Default.BabyChangingStation,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(180.dp)
            )
            Text(text = "Имя")
            AuthTextField(
                text = state.nameInput,
                onInputEvent = { onEvent(ProfileEvent.NameInput(it)) },
                placeholder = state.currentUser.name
            )
            Text(text = "Город")
            AuthTextField(
                text = state.cityInput,
                onInputEvent = { onEvent(ProfileEvent.CityInput(it)) },
                placeholder = user.city
            )
            Text(text = "Дата рождения")
            AuthTextField(
                text = state.birthdayInput,
                onInputEvent = { onEvent(ProfileEvent.BirthdayInput(it)) },
                placeholder = user.birthday,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            PrimaryButton(
                onClick = { onEvent(ProfileEvent.SaveChanges) },
                buttonText = "Сохранить изменения",
                enabled = state.birthdayInput.isNotEmpty() || state.cityInput.isNotEmpty() || state.nameInput.isNotEmpty()
            )
        }
    }
}
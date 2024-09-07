package com.zekony.windichat.ui.profile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zekony.windichat.R
import com.zekony.windichat.ui.authorization.ui.composables.PrimaryTextField
import com.zekony.windichat.ui.profile.mvi.ProfileEvent
import com.zekony.windichat.ui.profile.mvi.ProfileState
import com.zekony.windichat.utility.composables.PrimaryButton
import com.zekony.windichat.utility.constants.Constants

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
            AsyncImage(
                model = Constants.BASE_URL + state.currentUser.avatars.avatar,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(180.dp)
                    .clickable { onEvent(ProfileEvent.ChooseImage) }
            )
            Text(text = stringResource(R.string.name))
            PrimaryTextField(
                text = state.nameInput,
                onInputEvent = { onEvent(ProfileEvent.NameInput(it)) },
                placeholder = state.currentUser.name
            )
            Text(text = stringResource(R.string.city))
            PrimaryTextField(
                text = state.cityInput,
                onInputEvent = { onEvent(ProfileEvent.CityInput(it)) },
                placeholder = user.city
            )
            Text(text = stringResource(R.string.birthday))
            PrimaryTextField(
                text = state.birthdayInput,
                onInputEvent = { onEvent(ProfileEvent.BirthdayInput(it)) },
                placeholder = user.birthday,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            PrimaryButton(
                onClick = { onEvent(ProfileEvent.SaveChanges) },
                buttonText = stringResource(R.string.save_changes),
                enabled = state.birthdayInput.isNotEmpty() || state.cityInput.isNotEmpty() || state.nameInput.isNotEmpty()
            )
        }
    }
}
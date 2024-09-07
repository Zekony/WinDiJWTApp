package com.zekony.windichat.ui.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zekony.windichat.R
import com.zekony.windichat.ui.profile.mvi.ChangeInfo
import com.zekony.windichat.ui.profile.mvi.ProfileEvent
import com.zekony.windichat.ui.profile.mvi.ProfileState
import com.zekony.windichat.ui.profile.ui.composables.UserInfo
import com.zekony.windichat.utility.composables.PrimaryButton
import com.zekony.windichat.utility.constants.Constants

@Composable
fun ProfileScreen(state: ProfileState, onEvent: (ProfileEvent) -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.currentUser?.let { user ->
            if (state.currentUser.avatars.avatar.isNullOrEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.image_placeholderjpg),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable { onEvent(ProfileEvent.ChooseImage) }
                )
            } else {
                AsyncImage(
                    model = Constants.BASE_URL + state.currentUser.avatars.avatar,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable { onEvent(ProfileEvent.ChooseImage) }
                )

            }
            UserInfo(user)
            PrimaryButton(
                onClick = { onEvent(ProfileEvent.ChangeInfoType(ChangeInfo.Active)) },
                buttonText = stringResource(R.string.change_user_info)
            )
        }
    }
}


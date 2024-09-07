package com.zekony.windichat.ui.profile.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zekony.windichat.R
import com.zekony.windichat.domain.models.User

@Composable
fun UserInfo(user: User) {
    UserInfoCard {
        Text(text = stringResource(id = R.string.number))
        Text(text = user.phone)
    }
    UserInfoCard {
        Text(text = stringResource(id = R.string.name))
        Text(text = user.name)
    }
    UserInfoCard {
        Text(text = stringResource(id = R.string.username))
        Text(text = user.username)
    }
    UserInfoCard {
        Text(text = stringResource(id = R.string.city))
        Text(text = user.city.ifEmpty { stringResource(R.string.unspecified) })
    }
    UserInfoCard {
        Text(text = stringResource(id = R.string.number))
        Text(text = user.birthday.ifEmpty { stringResource(R.string.unspecified) })
    }
}

@Composable
fun UserInfoCard(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(CornerSize(15)))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        content()
    }
}
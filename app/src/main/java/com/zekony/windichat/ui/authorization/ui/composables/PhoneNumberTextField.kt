package com.zekony.windichat.ui.authorization.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.zekony.windichat.ui.authorization.mvi.AuthEvent
import com.zekony.windichat.ui.authorization.mvi.AuthState


@Composable
fun PhoneNumberTextField(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit
) {
    Row(modifier = Modifier.height(50.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { onEvent(AuthEvent.OpenCountryCodeDialog(true)) }
                .fillMaxWidth(0.3f)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(CornerSize(15))),
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.chosenCountry?.let {
                Image(
                    painter = painterResource(it.countryFlag),
                    contentDescription = null
                )
                Text(
                    text = it.countryPhoneNumberCode,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phoneInput,
            onValueChange = { onEvent(AuthEvent.PhoneNumberInput(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            textStyle = MaterialTheme.typography.titleMedium,
            placeholder = {
                Text(
                    text = "###-###-##-##",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        )
    }
}
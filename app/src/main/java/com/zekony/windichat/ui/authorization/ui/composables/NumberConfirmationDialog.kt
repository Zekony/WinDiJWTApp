package com.zekony.windichat.ui.authorization.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zekony.windichat.R
import com.zekony.windichat.ui.authorization.mvi.AuthEvent


@Composable
fun NumberConfirmationDialog(codeInput: String, onEvent: (AuthEvent) -> Unit) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.confirm_dialog_message),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                value = codeInput,
                onValueChange = { onEvent(AuthEvent.ConfCodeInput(it)) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background
                ),
                textStyle = MaterialTheme.typography.titleMedium
            )
            Button(
                onClick = { onEvent(AuthEvent.SendCode) }, shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = stringResource(R.string.ok), style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}
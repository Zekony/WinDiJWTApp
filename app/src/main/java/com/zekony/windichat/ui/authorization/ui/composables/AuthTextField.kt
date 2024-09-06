package com.zekony.windichat.ui.authorization.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthTextField(
    text: String,
    onInputEvent: (String) -> Unit,
    placeholder: String = "",
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { onInputEvent(it) },
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        textStyle = MaterialTheme.typography.titleMedium,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(placeholder, style = MaterialTheme.typography.titleMedium)
        }
    )
}
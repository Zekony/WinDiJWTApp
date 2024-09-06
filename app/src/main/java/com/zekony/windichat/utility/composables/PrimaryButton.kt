package com.zekony.windichat.utility.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(onClick: () -> Unit, buttonText: String, enabled: Boolean = true) {
    Button(
        onClick = { onClick() },
        enabled = enabled,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text = buttonText, style = MaterialTheme.typography.labelMedium)
    }
}
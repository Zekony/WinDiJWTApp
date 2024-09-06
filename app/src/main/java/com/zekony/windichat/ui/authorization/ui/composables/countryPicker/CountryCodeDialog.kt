package com.zekony.windichat.ui.authorization.ui.composables.countryPicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zekony.windichat.ui.authorization.mvi.AuthEvent

@Composable
fun CountryCodeDialog(
    onEvent: (AuthEvent) -> Unit,
    countryList: List<CountryDetails>
) {
    Dialog(onDismissRequest = { onEvent(AuthEvent.OpenCountryCodeDialog(false)) }) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically)
        ) {
            items(countryList) { country ->
                Column(
                    modifier = Modifier
                        .clickable { onEvent(AuthEvent.ChooseCountry(country)) }
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = country.countryFlag),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Text(text = country.countryName, textAlign = TextAlign.Center)
                }
            }
        }
    }
}
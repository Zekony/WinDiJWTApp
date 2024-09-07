package com.zekony.windichat.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomBar(navController: NavHostController) {
    val interactionSource = remember { MutableInteractionSource() }
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationButtons.values().forEach { navBtn ->
                val isSelected =
                    navController.currentBackStackEntryAsState().value?.destination?.route == navBtn.route
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navController.navigate(navBtn.route) {
                                launchSingleTop = true
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = if (isSelected) navBtn.activeIcon else navBtn.notActiveIcon,
                        contentDescription = stringResource(id = navBtn.screenName),
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = stringResource(id = navBtn.screenName),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
                    )
                }
            }
        }
    }
}
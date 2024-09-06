package com.zekony.windichat.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zekony.windichat.ui.authorization.AUTHORIZATION_ROUTE
import com.zekony.windichat.ui.authorization.authorizationEntry
import com.zekony.windichat.ui.chat.CHAT_ROUTE
import com.zekony.windichat.ui.chat.chatEntry
import com.zekony.windichat.ui.profile.PROFILE_ROUTE
import com.zekony.windichat.ui.profile.profileEntry
import com.zekony.windichat.ui.theme.WinDiChatTheme

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val noBottomBarScreens = listOf(
        AUTHORIZATION_ROUTE
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    WinDiChatTheme {
        Scaffold(
            bottomBar = {
                if (!noBottomBarScreens.contains(currentDestination)) AppBottomBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = AUTHORIZATION_ROUTE,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                },
                modifier = Modifier.padding(innerPadding)
            ) {
                authorizationEntry(
                    navigateToProfile = {
                        navController.navigate(PROFILE_ROUTE) {
                            popUpTo(AUTHORIZATION_ROUTE) { inclusive = true }
                        }
                    }
                )
                profileEntry(
                    navigateToAuthorization = {
                        navController.navigate(AUTHORIZATION_ROUTE) {
                            popUpTo(PROFILE_ROUTE) { inclusive = true }
                            popUpTo(CHAT_ROUTE) { inclusive = true }
                        }
                    }
                )
                chatEntry()
            }
        }
    }
}
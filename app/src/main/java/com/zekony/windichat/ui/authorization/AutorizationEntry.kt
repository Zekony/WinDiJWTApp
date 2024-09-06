package com.zekony.windichat.ui.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zekony.windichat.R
import com.zekony.windichat.ui.authorization.mvi.AuthSideEffect
import com.zekony.windichat.ui.authorization.mvi.AuthViewModel
import com.zekony.windichat.ui.authorization.mvi.InputError.EmailShouldContain
import com.zekony.windichat.ui.authorization.mvi.InputError.NameLength
import com.zekony.windichat.ui.authorization.mvi.InputError.PasswordLength
import com.zekony.windichat.ui.authorization.mvi.InputError.PasswordShouldContainSymbols
import com.zekony.windichat.ui.authorization.mvi.UserRegistrationState
import com.zekony.windichat.ui.authorization.ui.AuthorizationScreen
import com.zekony.windichat.ui.authorization.ui.LoadingScreen
import com.zekony.windichat.ui.authorization.ui.RegistrationScreen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

const val AUTHORIZATION_ROUTE = "authorization"

fun NavGraphBuilder.authorizationEntry(
    navigateToProfile: () -> Unit
) {
    composable(AUTHORIZATION_ROUTE) {
        val viewModel: AuthViewModel = hiltViewModel()
        val state by viewModel.collectAsState()
        val snackbarHost = remember { SnackbarHostState() }
        val context = LocalContext.current

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHost) }
        ) {
            Column(modifier = Modifier.padding(it)) {
                when (state.isRegistered) {
                    UserRegistrationState.FirstTime -> AuthorizationScreen(
                        state,
                        viewModel::dispatch
                    )

                    UserRegistrationState.HaveNumber -> RegistrationScreen(
                        state,
                        viewModel::dispatch
                    )

                    UserRegistrationState.MakingRequest -> LoadingScreen()
                }
            }
        }
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                AuthSideEffect.NavigateProfileScreen -> {
                    navigateToProfile()
                }

                AuthSideEffect.PostInputErrorMessage -> {
                    if (state.inputError != null)
                        snackbarHost.showSnackbar(
                            message = when (state.inputError) {
                                PasswordLength -> context.getString(R.string.password_is_too_short)
                                PasswordShouldContainSymbols -> context.getString(R.string.password_special_symbols_error)
                                EmailShouldContain -> context.getString(R.string.invalid_email_error)
                                NameLength -> context.getString(R.string.name_is_too_short_error)
                                null -> "" // Never reachable but still should be here
                            },
                        )
                }

                is AuthSideEffect.PostErrorMessage -> {
                    snackbarHost.showSnackbar(
                        sideEffect.message ?: context.getString(R.string.error_message)
                    )
                }
            }
        }
    }
}
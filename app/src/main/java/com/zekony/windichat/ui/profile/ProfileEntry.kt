package com.zekony.windichat.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.zekony.windichat.data.network.models.requests.updateUserRequest.Avatar
import com.zekony.windichat.ui.authorization.ui.LoadingScreen
import com.zekony.windichat.ui.profile.mvi.ChangeInfo
import com.zekony.windichat.ui.profile.mvi.DownloadState
import com.zekony.windichat.ui.profile.mvi.ProfileEvent
import com.zekony.windichat.ui.profile.mvi.ProfileSideEffect
import com.zekony.windichat.ui.profile.mvi.ProfileViewModel
import com.zekony.windichat.ui.profile.ui.ProfileChangingScreen
import com.zekony.windichat.ui.profile.ui.ProfileScreen
import com.zekony.windichat.utility.helperClasses.encodeImageToBase64
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

const val PROFILE_ROUTE = "profile"

fun NavGraphBuilder.profileEntry(
    navigateToAuthorization: () -> Unit
) {
    composable(PROFILE_ROUTE) {

        val viewModel: ProfileViewModel = hiltViewModel()
        val state by viewModel.collectAsState()
        val snackbarHost = remember { SnackbarHostState() }
        val context = LocalContext.current

        val launcherForUserImage = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                val userImage = encodeImageToBase64(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, selectedImageUri)
                } else {
                    BitmapFactory.decodeStream(context.contentResolver.openInputStream(selectedImageUri!!))
                })
                viewModel.dispatch(ProfileEvent.SaveImage(Avatar(userImage, selectedImageUri.toString())))
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHost) }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                if (state.downloadState == DownloadState.Downloading) {
                    LoadingScreen()
                } else {
                    when (state.changeInfoState) {
                        ChangeInfo.Active -> ProfileChangingScreen(state, viewModel::dispatch)
                        ChangeInfo.Disabled -> ProfileScreen(state, viewModel::dispatch)
                    }
                }
            }
        }


        BackHandler(state.changeInfoState == ChangeInfo.Active) {
            viewModel.dispatch(ProfileEvent.ChangeInfoType(ChangeInfo.Disabled))
        }
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ProfileSideEffect.Logout -> {
                    navigateToAuthorization()
                }

                ProfileSideEffect.ChooseImage -> {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    launcherForUserImage.launch(intent)
                }

                is ProfileSideEffect.PostErrorMessage -> {
                    snackbarHost.showSnackbar(
                        sideEffect.message ?: context.getString(R.string.error_message)
                    )
                }
            }
        }
    }
}



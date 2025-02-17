package br.com.hellodev.moviestreaming.presenter.features.profile.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.functions.checkAndRequestGalleryPermission
import br.com.hellodev.moviestreaming.core.functions.inputErrorMessage
import br.com.hellodev.moviestreaming.core.helper.MaskVisualTransformation
import br.com.hellodev.moviestreaming.core.helper.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.components.loading.LoadingScreenUI
import br.com.hellodev.moviestreaming.presenter.components.snackbar.FeedbackUI
import br.com.hellodev.moviestreaming.presenter.components.textfield.click.TextFieldClickUI
import br.com.hellodev.moviestreaming.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.features.profile.action.EditProfileAction
import br.com.hellodev.moviestreaming.presenter.features.profile.parameter.EditProfileParameter
import br.com.hellodev.moviestreaming.presenter.features.profile.state.EditProfileState
import br.com.hellodev.moviestreaming.presenter.features.profile.viewmodel.EditProfileViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    parameter: EditProfileParameter? = null,
    navigateToGenreScreen: () -> Unit,
    navigateToCountryScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<EditProfileViewModel>()
    val state by viewModel.state.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        parameter?.let { viewModel.submitAction(EditProfileAction.SetOnBackResult(it)) }
    }

    EditProfileContent(
        state = state,
        action = viewModel::submitAction,
        navigateToGenreScreen = navigateToGenreScreen,
        navigateToCountryScreen = navigateToCountryScreen,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun EditProfileContent(
    state: EditProfileState,
    action: (EditProfileAction) -> Unit,
    navigateToGenreScreen: () -> Unit,
    navigateToCountryScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            action(EditProfileAction.SetImageUri(uri))
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            // Exibir aviso caso a permissão seja negada
        }
    }

    LaunchedEffect(state.hasFeedback) {
        if (state.hasFeedback) {
            scope.launch {
                val result = snackbarHostState
                    .showSnackbar(
                        message = context.getString(
                            state.feedbackUI?.second ?: R.string.error_generic
                        )
                    )

                if (result == SnackbarResult.Dismissed) {
                    action(EditProfileAction.ClearFeedback)
                }
            }
        }
    }

    when {
        state.isLoadingScreen -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingScreenUI()
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBarUI(
                        title = stringResource(R.string.label_title_edit_profile_screen),
                        onClick = onBackPressed
                    )
                },
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .windowInsetsPadding(WindowInsets.navigationBars)
                            .background(
                                MovieStreamingTheme.colorScheme.primaryBackgroundColor.copy(
                                    alpha = 0.7f
                                )
                            )
                    ) {
                        HorizontalDividerUI()

                        PrimaryButton(
                            modifier = Modifier
                                .padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = 24.dp,
                                    bottom = 32.dp
                                ),
                            text = stringResource(R.string.label_button_update_edit_profile_screen),
                            isLoading = state.isLoading,
                            onClick = { action(EditProfileAction.Update) }
                        )
                    }
                },
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        snackbar = { snackbarData ->
                            state.feedbackUI?.let { feedbackUI ->
                                FeedbackUI(
                                    message = snackbarData.visuals.message,
                                    type = feedbackUI.first
                                )
                            }
                        }
                    )
                },
                containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(paddingValues)
                            .padding(24.dp)
                            .imePadding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ImageUI(
                            modifier = Modifier
                                .size(140.dp),
                            imageModel = state.imageUri,
                            contentScale = ContentScale.Crop,
                            previewPlaceholder = painterResource(id = R.drawable.placeholder_welcome),
                            shape = CircleShape,
                            isLoading = state.isLoadingScreen,
                            onClick = {
                                checkAndRequestGalleryPermission(
                                    context = context,
                                    launcher = permissionLauncher,
                                    onPermissionGranted = {
                                        imagePickerLauncher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                                )
                            }
                        )

                        TextFieldUI(
                            value = state.name,
                            isError = state.inputError == InputType.FIRST_NAME,
                            error = stringResource(inputErrorMessage(InputType.FIRST_NAME)),
                            placeholder = stringResource(R.string.label_input_first_name_edit_profile_screen),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                action(EditProfileAction.OnNameChanged(it))
                            }
                        )

                        TextFieldUI(
                            value = state.surname,
                            isError = state.inputError == InputType.SURNAME,
                            error = stringResource(inputErrorMessage(InputType.SURNAME)),
                            placeholder = stringResource(R.string.label_input_surname_edit_profile_screen),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            onValueChange = {
                                action(EditProfileAction.OnSurnameChanged(it))
                            }
                        )

                        TextFieldUI(
                            value = state.email,
                            placeholder = stringResource(R.string.label_input_email_edit_profile_screen),
                            enabled = false,
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            },
                            onValueChange = {

                            }
                        )

                        TextFieldUI(
                            value = state.phone,
                            isError = state.inputError == InputType.PHONE,
                            error = stringResource(inputErrorMessage(InputType.PHONE)),
                            placeholder = stringResource(R.string.label_input_phone_edit_profile_screen),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone
                            ),
                            maxLength = MaskVisualTransformation.PHONE_MASK_SIZE,
                            visualTransformation = MaskVisualTransformation(PHONE_MASK),
                            onValueChange = {
                                action(EditProfileAction.OnPhoneChanged(it))
                            }
                        )

                        TextFieldClickUI(
                            value = state.genre ?: "",
                            placeholder = stringResource(R.string.label_input_genre_edit_profile_screen),
                            painter = painterResource(id = R.drawable.ic_right),
                            isError = state.inputError == InputType.GENRE,
                            error = stringResource(inputErrorMessage(InputType.GENRE)),
                            onClick = navigateToGenreScreen
                        )

                        TextFieldClickUI(
                            value = state.country ?: "",
                            placeholder = stringResource(R.string.label_input_country_edit_profile_screen),
                            painter = painterResource(id = R.drawable.ic_right),
                            isError = state.inputError == InputType.COUNTRY,
                            error = stringResource(inputErrorMessage(InputType.COUNTRY)),
                            onClick = navigateToCountryScreen
                        )
                    }
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun EditProfile() {
    MovieStreamingTheme {
        EditProfileContent(
            state = EditProfileState(
                isLoadingScreen = false,
                email = "u@gmail.com",
                phone = "11912345678"
            ),
            action = {},
            navigateToGenreScreen = {},
            navigateToCountryScreen = {},
            onBackPressed = {}
        )
    }
}











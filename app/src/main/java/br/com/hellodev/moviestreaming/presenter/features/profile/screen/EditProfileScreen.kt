package br.com.hellodev.moviestreaming.presenter.features.profile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.functions.inputErrorMessage
import br.com.hellodev.moviestreaming.core.helper.MaskVisualTransformation
import br.com.hellodev.moviestreaming.core.helper.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.components.textfield.click.TextFieldClickUI
import br.com.hellodev.moviestreaming.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.features.profile.action.EditProfileAction
import br.com.hellodev.moviestreaming.presenter.features.profile.state.EditProfileState
import br.com.hellodev.moviestreaming.presenter.features.profile.viewmodel.EditProfileViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<EditProfileViewModel>()
    val state by viewModel.state.collectAsState()

    EditProfileContent(
        state = state,
        action = viewModel::submitAction,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun EditProfileContent(
    state: EditProfileState,
    action: (EditProfileAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_edit_profile_screen),
                onClick = onBackPressed
            )
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ImageUI(
                    modifier = Modifier
                        .size(140.dp),
                    imageModel = null,
                    contentScale = ContentScale.Crop,
                    previewPlaceholder = painterResource(id = R.drawable.placeholder_welcome),
                    shape = CircleShape,
                    isLoading = state.isLoading,
                    onClick = {}
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
                    value = "",
                    placeholder = stringResource(R.string.label_input_email_edit_profile_screen),
                    trailingIcon = {
                        IconButton(
                            onClick = {},
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
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
                    value = state.genre,
                    placeholder = stringResource(R.string.label_input_genre_edit_profile_screen),
                    painter = painterResource(id = R.drawable.ic_right),
                    isError = state.inputError == InputType.GENRE,
                    error = stringResource(inputErrorMessage(InputType.GENRE)),
                    onClick = {

                    }
                )

                TextFieldClickUI(
                    value = state.country,
                    placeholder = stringResource(R.string.label_input_country_edit_profile_screen),
                    painter = painterResource(id = R.drawable.ic_right),
                    isError = state.inputError == InputType.COUNTRY,
                    error = stringResource(inputErrorMessage(InputType.COUNTRY)),
                    onClick = {}
                )

                PrimaryButton(
                    text = stringResource(R.string.label_button_update_edit_profile_screen),
                    isLoading = false,
                    enabled = true,
                    onClick = { action(EditProfileAction.Update) }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun EditProfile() {
    MovieStreamingTheme {
        EditProfileContent(
            state = EditProfileState(),
            action = {},
            onBackPressed = {}
        )
    }
}











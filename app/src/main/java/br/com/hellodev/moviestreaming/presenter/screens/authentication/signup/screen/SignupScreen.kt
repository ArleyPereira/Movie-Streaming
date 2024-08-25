package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.InputType
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.button.SocialButton
import br.com.hellodev.moviestreaming.presenter.components.divider.HorizontalDividerWithText
import br.com.hellodev.moviestreaming.presenter.components.textfield.TextFieldUI
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.action.SignupAction
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.state.SignupState
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.viewmodel.SignupViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun SignupScreen(
    onBackPressed: () -> Unit,
) {
    val viewModel: SignupViewModel = viewModel()
    val state = viewModel.state.collectAsState().value

    SignupContent(
        state = state,
        action = viewModel::submitAction,
        onBackPressed = {}
    )
}

@Composable
fun SignupContent(
    state: SignupState,
    action: (SignupAction) -> Unit,
    onBackPressed: () -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBarUI(
                onClick = {}
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieStreamingTheme.colorScheme.backgroundColor)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                )

                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = stringResource(id = R.string.label_title_signup_screen),
                    style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 38.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight.Bold,
                        color = MovieStreamingTheme.colorScheme.textColor,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(48.dp))

                TextFieldUI(
                    modifier = Modifier,
                    value = state.email,
                    placeholder = stringResource(id = R.string.label_input_email_signup_screen),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    onValueChange = {
                        action(
                            SignupAction.OnValueChange(
                                value = it,
                                type = InputType.EMAIL
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldUI(
                    modifier = Modifier,
                    value = state.password,
                    placeholder = stringResource(id = R.string.label_input_password_signup_screen),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    trailingIcon = {
                        if (state.password.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    showPassword = !showPassword
                                },
                                content = {
                                    Icon(
                                        painter = if (showPassword) {
                                            painterResource(id = R.drawable.ic_hide)
                                        } else {
                                            painterResource(id = R.drawable.ic_show)
                                        },
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                }
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    onValueChange = {
                        action(
                            SignupAction.OnValueChange(
                                value = it,
                                type = InputType.PASSWORD
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                PrimaryButton(
                    text = stringResource(id = R.string.label_button_signup_screen),
                    isLoading = false,
                    enabled = true,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(20.dp))

                HorizontalDividerWithText(
                    text = stringResource(id = R.string.label_or_signup_screen)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SocialButton(
                        icon = painterResource(id = R.drawable.ic_google),
                        isLoading = false,
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    SocialButton(
                        icon = painterResource(id = R.drawable.ic_facebook),
                        isLoading = false,
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    SocialButton(
                        icon = painterResource(id = R.drawable.ic_github),
                        isLoading = false,
                        onClick = {}
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.label_sign_up_account_signup_screen),
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            color = MovieStreamingTheme.colorScheme.textColor,
                            textAlign = TextAlign.Right,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(id = R.string.label_sign_in_signup_screen),
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = MovieStreamingTheme.colorScheme.defaultColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SignupPreview() {
    MovieStreamingTheme {
        SignupContent(
            state = SignupState(),
            action = {},
            onBackPressed = {}
        )
    }
}
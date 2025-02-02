package br.com.hellodev.moviestreaming.presenter.components.textfield.default

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.helper.MaskVisualTransformation
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun TextFieldUI(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    error: String = "",
    singleLine: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    requireKeyboardFocus: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = MovieStreamingTheme.colorScheme.defaultColor,
        backgroundColor = MovieStreamingTheme.colorScheme.alphaDefaultColor
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            TextField(
                value = value,
                onValueChange = { value ->
                    val filteredValue = when (visualTransformation) {
                        is MaskVisualTransformation -> value.filter { it.isDigit() }

                        else -> value
                    }

                    if (filteredValue.length <= maxLength) {
                        onValueChange(filteredValue)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isError) {
                            MovieStreamingTheme.colorScheme.defaultColor
                        } else {
                            MovieStreamingTheme.colorScheme.transparentColor
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .focusRequester(focusRequester),
                enabled = enabled,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            color = MovieStreamingTheme.colorScheme.greyscale500Color,
                            letterSpacing = 0.2.sp
                        )
                    )
                },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                singleLine = singleLine,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = MovieStreamingTheme.colorScheme.textFieldBackgroundColor,
                    unfocusedContainerColor = MovieStreamingTheme.colorScheme.textFieldBackgroundColor,
                    focusedContainerColor = MovieStreamingTheme.colorScheme.textFieldBackgroundColor,
                    focusedIndicatorColor = MovieStreamingTheme.colorScheme.transparentColor,
                    unfocusedIndicatorColor = MovieStreamingTheme.colorScheme.transparentColor,
                    errorContainerColor = MovieStreamingTheme.colorScheme.alphaDefaultColor,
                    errorIndicatorColor = MovieStreamingTheme.colorScheme.transparentColor,
                    disabledTextColor = MovieStreamingTheme.colorScheme.disabledColor,
                    unfocusedTextColor = MovieStreamingTheme.colorScheme.textColor,
                    focusedTextColor = MovieStreamingTheme.colorScheme.textColor,
                    errorTextColor = MovieStreamingTheme.colorScheme.textColor,
                    cursorColor = MovieStreamingTheme.colorScheme.defaultColor
                )
            )
        }

        if (isError) {
            Text(
                text = error,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = UrbanistFamily,
                    color = MovieStreamingTheme.colorScheme.defaultColor,
                    letterSpacing = 0.2.sp
                )
            )
        }

        if (requireKeyboardFocus) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TextFieldUIPreview() {
    var textValue by remember {
        mutableStateOf("")
    }

    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                isError = false,
                enabled = false,
                placeholder = "Ex: Arley Santana",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )
                },
                onValueChange = {
                    textValue = it
                }
            )

            TextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                isError = false,
                placeholder = "Ex: Arley Santana",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )
                },
                onValueChange = {
                    textValue = it
                }
            )

            TextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                isError = true,
                error = stringResource(R.string.error_first_name_invalid),
                placeholder = "Ex: Arley Santana",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )
                },
                onValueChange = {
                    textValue = it
                }
            )
        }
    }
}
package br.com.hellodev.moviestreaming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

private val LightColorScheme = MyColorScheme(
    defaultColor = DefaultColor,
    backgroundColor = BackgroundColorLight,
    textColor = TextColorLight,
    secondaryButtonColor = SecondaryButtonColorLight,
    secondaryButtonTextColor = SecondaryButtonTextColorLight,
    successColor = SuccessColor,
    errorColor = ErrorColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    whiteColor = WhiteColor,
    blackColor = BlackColor
)

private val DarkColorScheme = MyColorScheme(
    defaultColor = DefaultColor,
    backgroundColor = BackgroundColorDark,
    textColor = TextColorDark,
    secondaryButtonColor = SecondaryButtonColorDark,
    secondaryButtonTextColor = SecondaryButtonTextColorDark,
    successColor = SuccessColor,
    errorColor = ErrorColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    whiteColor = WhiteColor,
    blackColor = BlackColor
)

private val LocalColorScheme = compositionLocalOf { LightColorScheme }

object MovieStreamingTheme {
    val colorScheme: MyColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

@Composable
fun MovieStreamingTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme by remember(isDarkTheme) {
        mutableStateOf(if (isDarkTheme) DarkColorScheme else LightColorScheme)
    }

    CompositionLocalProvider(LocalColorScheme provides colorScheme) {
        MaterialTheme(content = content)
    }
}
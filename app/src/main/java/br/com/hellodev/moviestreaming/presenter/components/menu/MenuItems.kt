package br.com.hellodev.moviestreaming.presenter.components.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.menu.MenuType

sealed class MenuItems(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    type: MenuType
) {

    data object EditProfile: MenuItems(
        icon = R.drawable.ic_edit,
        label = R.string.label_edit_account_screen,
        type = MenuType.EDIT_PROFILE
    )

    data object Notification: MenuItems(
        icon = R.drawable.ic_notification,
        label = R.string.label_notification_account_screen,
        type = MenuType.NOTIFICATION
    )

    data object Download: MenuItems(
        icon = R.drawable.ic_download_line,
        label = R.string.label_download_account_screen,
        type = MenuType.DOWNLOAD
    )

    data object Security: MenuItems(
        icon = R.drawable.ic_security,
        label = R.string.label_security_account_screen,
        type = MenuType.SECURITY
    )

    data object Language: MenuItems(
        icon = R.drawable.ic_language,
        label = R.string.label_language_account_screen,
        type = MenuType.LANGUAGE
    )

    data object DarkMode: MenuItems(
        icon = R.drawable.ic_theme_mode,
        label = R.string.label_theme_mode_account_screen,
        type = MenuType.DARK_MODE
    )

    data object HelpCenter: MenuItems(
        icon = R.drawable.ic_info,
        label = R.string.label_help_center_account_screen,
        type = MenuType.HELP_CENTER
    )

    data object PrivacyPolicy: MenuItems(
        icon = R.drawable.ic_privacy_policy,
        label = R.string.label_privacy_policy_account_screen,
        type = MenuType.PRIVACY_POLICY
    )

    data object Logout: MenuItems(
        icon = R.drawable.ic_logout,
        label = R.string.label_logout_account_screen,
        type = MenuType.LOGOUT
    )

    companion object {
        fun items() = listOf(
            EditProfile,
            Notification,
            Download,
            Security,
            Language,
            DarkMode,
            HelpCenter,
            PrivacyPolicy,
            Logout
        )
    }

}
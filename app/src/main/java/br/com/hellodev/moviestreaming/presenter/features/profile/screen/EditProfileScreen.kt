package br.com.hellodev.moviestreaming.presenter.features.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
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











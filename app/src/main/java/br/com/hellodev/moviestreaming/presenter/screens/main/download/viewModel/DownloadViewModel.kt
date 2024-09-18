package br.com.hellodev.moviestreaming.presenter.screens.main.download.viewModel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.presenter.screens.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.screens.main.download.state.DownloadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DownloadViewModel : ViewModel() {

    private val _state = MutableStateFlow(DownloadState())
    val state = _state.asStateFlow()

    fun submitAction(action: DownloadAction) {
    }


}
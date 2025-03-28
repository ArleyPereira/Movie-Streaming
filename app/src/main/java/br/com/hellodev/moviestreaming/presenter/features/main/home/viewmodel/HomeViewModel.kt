package br.com.hellodev.moviestreaming.presenter.features.main.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetNowPlayingUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.home.action.HomeAction
import br.com.hellodev.moviestreaming.presenter.features.main.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getNowPlaying()
    }

    fun submitAction(action: HomeAction) {

    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            val response = getNowPlayingUseCase()

            when(response.resultStatus){
                ResultStatus.SUCCESS ->  {
                    _state.update {
                        it.copy(nowPlayingList = response.results ?: emptyList())
                    }
                }

                else -> {

                }
            }

        }
    }

}
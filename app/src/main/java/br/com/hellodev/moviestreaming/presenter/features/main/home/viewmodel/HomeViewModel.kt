package br.com.hellodev.moviestreaming.presenter.features.main.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetNowPlayingUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetPopularUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetTopRatedUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.home.action.HomeAction
import br.com.hellodev.moviestreaming.presenter.features.main.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getNowPlaying()
        getPopular()
        getTopRated()
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

    private fun getPopular() {
        viewModelScope.launch {
            val response = getPopularUseCase()

            when(response.resultStatus){
                ResultStatus.SUCCESS ->  {
                    _state.update {
                        it.copy(popularList = response.results ?: emptyList())
                    }
                }

                else -> {

                }
            }

        }
    }

    private fun getTopRated() {
        viewModelScope.launch {
            val response = getTopRatedUseCase()

            when(response.resultStatus){
                ResultStatus.SUCCESS ->  {
                    _state.update {
                        it.copy(topRatedList = response.results ?: emptyList())
                    }
                }

                else -> {

                }
            }

        }
    }

}
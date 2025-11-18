package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.LoginUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.RegisterUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.credits.GetMovieCreditsUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.DeleteMovieUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.GetMoviesUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.SaveMovieUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.DeleteFavoriteUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.GetFavoriteByIdUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.GetFavoritesUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.InsertFavoriteUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetMovieDetailsUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetNowPlayingUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetPopularUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetTopRatedUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetUpcomingUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.SearchMovieUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.reviews.GetMovieReviewsUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.GetUserUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveImageUserUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::RegisterUseCase)

    factoryOf(::LoginUseCase)

    factoryOf(::SaveUserUseCase)

    factoryOf(::GetUserUseCase)

    factoryOf(::SaveImageUserUseCase)

    factoryOf(::GetNowPlayingUseCase)

    factoryOf(::GetPopularUseCase)

    factoryOf(::GetTopRatedUseCase)

    factoryOf(::GetUpcomingUseCase)

    factoryOf(::GetMovieDetailsUseCase)

    factoryOf(::GetMovieCreditsUseCase)

    factoryOf(::GetMovieReviewsUseCase)

    factoryOf(::SearchMovieUseCase)

    factoryOf(::SaveMovieUseCase)

    factoryOf(::GetMoviesUseCase)

    factoryOf(::DeleteMovieUseCase)

    // ######################### Favorite - Local #########################

    factoryOf(::InsertFavoriteUseCase)

    factoryOf(::DeleteFavoriteUseCase)

    factoryOf(::GetFavoritesUseCase)

    factoryOf(::GetFavoriteByIdUseCase)

}
package br.com.hellodev.moviestreaming.data.routes

const val NOW_PLAYING_ROUTE = "movie/now_playing"

const val POPULAR_ROUTE = "movie/popular"

const val TOP_RATED_ROUTE = "movie/top_rated"

const val UPCOMING_ROUTE = "movie/upcoming"

fun movieDetailsRoute(movieId: Int) = "movie/$movieId"

fun movieCreditsRoute(movieId: Int) = "movie/$movieId/credits"

fun movieReviewsRoute(movieId: Int) = "movie/$movieId/reviews"

const val MOVIE_SEARCH_ROUTE = "search/movie"
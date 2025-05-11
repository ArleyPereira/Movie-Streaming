package br.com.hellodev.moviestreaming.core.navigation.tabs

import androidx.annotation.StringRes
import br.com.hellodev.moviestreaming.R

sealed class MovieDetailsTabsItems(
    @StringRes val title: Int
) {

    data object Trailers : MovieDetailsTabsItems(
        title = R.string.label_trailers_tab_movie_details_screen
    )

    data object Similar : MovieDetailsTabsItems(
        title = R.string.label_similar_tab_movie_details_screen
    )

    data object Comments : MovieDetailsTabsItems(
        title = R.string.label_comments_tab_movie_details_screen
    )

    companion object {
        val items = listOf(Trailers, Similar, Comments)
    }

}
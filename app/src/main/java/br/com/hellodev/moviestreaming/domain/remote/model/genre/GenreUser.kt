package br.com.hellodev.moviestreaming.domain.remote.model.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreUser(
    val id: Int? = null,
    val name: String? = null
) {
    companion object {
        val items = listOf(
            GenreUser(id = 1, name = "Masculino"),
            GenreUser(id = 2, name = "Feminino")
        )
    }
}

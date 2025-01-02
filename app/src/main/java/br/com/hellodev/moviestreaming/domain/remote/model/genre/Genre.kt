package br.com.hellodev.moviestreaming.domain.remote.model.genre

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int? = null,
    val name: String? = null
) {
    companion object {
        val items = listOf(
            Genre(id = 1, name = "Masculino"),
            Genre(id = 2, name = "Feminino")
        )
    }
}

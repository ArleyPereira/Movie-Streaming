package br.com.hellodev.moviestreaming.domain.remote.model.genre

data class Genre(
    val id: Int? = null,
    val name: String? = null
) {
    companion object {
        val genres = listOf(
            Genre(id = 1, name = "Masculino"),
            Genre(id = 2, name = "Feminino")
        )
    }
}

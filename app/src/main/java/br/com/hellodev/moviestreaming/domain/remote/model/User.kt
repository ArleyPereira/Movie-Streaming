package br.com.hellodev.moviestreaming.domain.remote.model

data class User(
    val id: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val photo: String? = null,
    val phone: String? = null,
    val genre: String? = null
)

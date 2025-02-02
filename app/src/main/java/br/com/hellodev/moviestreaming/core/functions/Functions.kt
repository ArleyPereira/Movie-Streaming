package br.com.hellodev.moviestreaming.core.functions

import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.enums.input.InputType.COUNTRY
import br.com.hellodev.moviestreaming.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.moviestreaming.core.enums.input.InputType.GENRE
import br.com.hellodev.moviestreaming.core.enums.input.InputType.PHONE
import br.com.hellodev.moviestreaming.core.enums.input.InputType.SURNAME
import java.text.Normalizer
import java.util.Locale

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    return email.matches(emailRegex.toRegex())
}

fun isValidName(name: String): Boolean {
    val regex = "^[a-zA-Z]{3,}$".toRegex()
    return regex.matches(name)
}

fun isValidPhone(phone: String): Boolean {
    val regex = """^\d{2}9\d{8}$""".toRegex()
    return regex.matches(phone)
}

fun inputErrorMessage(type: InputType?): Int {
    return when (type) {
        FIRST_NAME -> R.string.error_first_name_invalid
        SURNAME -> R.string.error_surname_invalid
        PHONE -> R.string.error_phone_invalid
        GENRE -> R.string.error_genre_invalid
        COUNTRY -> R.string.error_country_invalid
        else -> R.string.error_generic
    }
}

fun String.normalize(): String {
    return Normalizer.normalize(this, Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}"), "")
        .lowercase(Locale.getDefault())
}
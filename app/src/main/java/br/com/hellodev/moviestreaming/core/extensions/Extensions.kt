package br.com.hellodev.moviestreaming.core.extensions

fun Float.calculateFileSize(): String {
    val value = this * 10.0
    return if (value >= 1000) {
        val result = (value / 1000.0 * 100).toInt() / 100.0
        "$result GB"
    } else {
        val result = (value * 10).toInt() / 10.0
        "$result MB"
    }
}

fun Int.calculateMovieTime(): String {
    val hours = this / 60
    val minutes = this % 60
    return "${hours}h ${minutes}m"
}
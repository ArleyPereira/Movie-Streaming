package br.com.hellodev.moviestreaming.core.navigation.extensions

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> SavedStateHandle.putObject(key: String, value: T) {
    val json = Json.encodeToString(value)
    this[key] = json
}

inline fun <reified T> SavedStateHandle.getObject(key: String): T? {
    val json = this.get<String>(key)
    return json?.let { Json.decodeFromString(it) }
}
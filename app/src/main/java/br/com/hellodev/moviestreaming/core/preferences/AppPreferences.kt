package br.com.hellodev.moviestreaming.core.preferences

import android.content.Context
import br.com.hellodev.moviestreaming.core.constans.SharedPreferenceKeys.FILE_NAME
import br.com.hellodev.moviestreaming.core.constans.SharedPreferenceKeys.WELCOME_VISITED

class AppPreferences(context: Context) {

    private val sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun saveWelcomeVisited(visited: Boolean) {
        sharedPref.edit().putBoolean(WELCOME_VISITED, visited).apply()
    }

    fun getWelcomeVisited(): Boolean {
        return sharedPref.getBoolean(WELCOME_VISITED, false)
    }

}
package br.com.hellodev.moviestreaming.core.helper

import br.com.hellodev.moviestreaming.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {
    companion object {
        fun getAuth() = FirebaseAuth.getInstance()

        fun getDatabase() = FirebaseDatabase.getInstance().reference

        fun getUserId() = getAuth().currentUser?.uid.orEmpty()

        fun validError(error: String?): Int {
            return when {
                error?.contains("The email address is already in use by another account") == true -> {
                    R.string.email_in_use_firebase_authentication
                }

                error?.contains("Password should be at least 6 characters") == true -> {
                    R.string.strong_password_firebase_authentication
                }

                error?.contains("The email address is badly formatted") == true -> {
                    R.string.invalid_email_firebase_authentication
                }

                else -> {
                    R.string.error_generic
                }
            }
        }
    }
}
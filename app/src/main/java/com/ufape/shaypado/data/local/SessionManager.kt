package com.ufape.shaypado.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ufape.shaypado.data.model.LoginResponse

class SessionManager(private val prefs: SharedPreferences) : ISessionManager {
    companion object {
        private const val USER = "user"
    }


    private val editor: SharedPreferences.Editor = prefs.edit()
    private val gson = Gson()

    override fun removeUser() {
        editor.remove(USER).apply()
    }


    override fun saveUser(user: LoginResponse) {
        val jsonUser = gson.toJson(user)
        editor.putString(USER, jsonUser)
        editor.apply()
    }

    override fun fetchUser(): LoginResponse? {
        val user = prefs.getString(USER, null)
        return gson.fromJson(user, LoginResponse::class.java)
    }

    /**
     * Function to fetch auth token from the [SharedPreferences]
     *
     * @return the token, or null if the token doesn't exist
     */
    override fun fetchAuthToken(): String? {
        val user = prefs.getString(USER, null)
        val token = gson.fromJson(user, LoginResponse::class.java).token
        return if (isTokenValid(token)) {
            token
        } else {
            null
        }
    }

    private fun isTokenValid(token: String?): Boolean {
       return !token.isNullOrEmpty()
    }
}

interface ISessionManager {
    fun fetchAuthToken(): String?

    fun saveUser(user: LoginResponse)

    fun fetchUser(): LoginResponse?

    fun removeUser()
}
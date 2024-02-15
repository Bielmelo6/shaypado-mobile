package com.ufape.shaypado.data

import com.ufape.shaypado.data.local.ISessionManager
import kotlinx.coroutines.*
import okhttp3.*

class TokenAuthenticator(
    private val sessionManager: ISessionManager,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            runBlocking {
                if (response.code == 401) {
                    sessionExpired()
                }
            }
        }
        return null
    }

    private suspend fun sessionExpired() {
        (Dispatchers.Main){
            GlobalNavigator.sessionExpired()
        }
    }

}

/**
 * Helper object, it is attached to a handler, if the session is expired, the [TokenAuthenticator]
 * will cal the method [sessionExpired]
 */
object GlobalNavigator {

    private var handler: GlobalNavigationHandler? = null

    fun registerHandler(handler: GlobalNavigationHandler) {
        GlobalNavigator.handler = handler
    }

    fun sessionExpired() {
        handler?.sessionExpired()
    }
}

interface GlobalNavigationHandler {
    fun sessionExpired()
}
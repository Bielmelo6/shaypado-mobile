package com.ufape.shaypado.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.LoginData
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.ui.domain.use_case.hasError
import com.ufape.shaypado.ui.domain.use_case.validateEmail
import com.ufape.shaypado.ui.domain.use_case.validatePassword
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
) : ViewModel() {
    var loginDataState by mutableStateOf(LoginFormState())

    private val loginEventChannel = Channel<Result<LoginResponse>>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    fun onLoginDataEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.OnEmailChanged -> {
                loginDataState = loginDataState.copy(email = event.email)
            }

            is LoginFormEvent.OnPasswordChanged -> {
                loginDataState = loginDataState.copy(password = event.password)
            }

            is LoginFormEvent.OnSubmit -> {
                login()
            }

        }
    }

    private fun validate() : Boolean {
        val emailValidation = validateEmail(loginDataState.email)
        val passwordValidation = validatePassword(loginDataState.password)

        loginDataState = loginDataState.copy(
            emailError = emailValidation.error,
            passwordError = passwordValidation.error,
        )

        return hasError(
            emailValidation,
            passwordValidation
        )
    }

    fun login(){
        val hasError = validate()

        if (hasError) return

        viewModelScope.launch {
            val loginData = LoginData(
                email = loginDataState.email,
                password = loginDataState.password,
            )
            val result = authRepository.login(loginData)
            loginEventChannel.send(result)
        }
    }
}
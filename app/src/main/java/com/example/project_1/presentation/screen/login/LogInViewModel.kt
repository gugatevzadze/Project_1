package com.example.project_1.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.common.Resource
import com.example.project_1.domain.usecase.auth.LoginUseCase
import com.example.project_1.domain.usecase.datastore.SaveSessionDataStoreUseCase
import com.example.project_1.domain.usecase.validator.EmailValidatorUseCase
import com.example.project_1.domain.usecase.validator.FieldsValidatorUseCase
import com.example.project_1.domain.usecase.validator.PasswordValidatorUseCase
import com.example.project_1.presentation.event.login.LogInEvent
import com.example.project_1.presentation.state.login.LogInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveSessionDataStoreUseCase: SaveSessionDataStoreUseCase,
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase,
    private val fieldsValidatorUseCase: FieldsValidatorUseCase
) : ViewModel() {

    private val _logInState = MutableStateFlow(LogInState())
    val logInState: SharedFlow<LogInState> = _logInState.asStateFlow()

    private val _loginNavigationEvent = MutableSharedFlow<LogInNavigationEvent>()
    val loginNavigationEvent: SharedFlow<LogInNavigationEvent> get() = _loginNavigationEvent

    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.LogIn -> logIn(email = event.email, password = event.password, rememberMeChecked = false)
            is LogInEvent.LogInWithRememberMe -> logIn(email = event.email, password = event.password, rememberMeChecked = true)
            is LogInEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }


    private fun logIn(email: String, password: String, rememberMeChecked: Boolean) {
        viewModelScope.launch {
            if (validationChecker(email, password)) {
                loginUseCase(email = email, password = password).collect {
                    when (it) {
                        is Resource.Loading -> _logInState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }

                        is Resource.Success -> {
                            _logInState.update { currentState -> currentState.copy() }
                            saveSessionDataStoreUseCase(value = rememberMeChecked)
                            _loginNavigationEvent.emit(LogInNavigationEvent.NavigateToList)
                        }

                        is Resource.Error -> updateErrorMessage(message = it.errorMessage)
                    }
                }
            }
        }
    }


    private fun validationChecker(email: String, password: String): Boolean {
        return when {
            !fieldsValidatorUseCase.validateFields(email, password) -> {
                updateErrorMessage(message = "Please fill in all fields")
                false
            }
            !emailValidatorUseCase.isEmailValid(email) -> {
                updateErrorMessage(message = "Please enter a valid email address")
                false
            }
            !passwordValidatorUseCase.isPasswordValid(password) -> {
                updateErrorMessage(message = "Please enter a valid password")
                false
            }
            else -> true
        }
    }


    private fun updateErrorMessage(message: String?) {
        _logInState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface LogInNavigationEvent {
        data object NavigateToList : LogInNavigationEvent
    }
}
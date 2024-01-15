package com.example.project_1.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.domain.usecase.datastore.ReadSessionDataStoreUseCase
import com.example.project_1.presentation.event.welcome.WelcomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val readSessionDataStoreUseCase: ReadSessionDataStoreUseCase) :
    ViewModel() {
    private val _welcomeNavigationEvent = MutableSharedFlow<WelcomeNavigationEvent>()
    val welcomeNavigationEvent: SharedFlow<WelcomeNavigationEvent> get() = _welcomeNavigationEvent

    fun onEvent(event: WelcomeEvent) {
        when (event) {
            is WelcomeEvent.LoginButtonClicked -> navigateToLogin()
            is WelcomeEvent.RegisterButtonClicked -> navigateToRegister()
        }
    }

    init {
        readSession()
    }

    private fun readSession() {
        viewModelScope.launch {
            readSessionDataStoreUseCase().collect { isSessionActive ->
                if (isSessionActive) {
                    navigateToList()
                }
            }
        }
    }

    private fun navigateToList(){
        viewModelScope.launch {
            _welcomeNavigationEvent.emit(WelcomeNavigationEvent.NavigateToList)
        }
    }

    private fun navigateToLogin(){
        viewModelScope.launch {
            _welcomeNavigationEvent.emit(WelcomeNavigationEvent.NavigateToLogin)
        }
    }

    private fun navigateToRegister(){
        viewModelScope.launch {
            _welcomeNavigationEvent.emit(WelcomeNavigationEvent.NavigateToRegister)
        }
    }


    sealed interface WelcomeNavigationEvent{
        data object NavigateToLogin : WelcomeNavigationEvent
        data object NavigateToRegister: WelcomeNavigationEvent
        data object NavigateToList: WelcomeNavigationEvent
    }
}
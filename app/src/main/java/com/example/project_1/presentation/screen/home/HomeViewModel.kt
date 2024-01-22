package com.example.project_1.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.domain.usecase.auth.LogoutUseCase
import com.example.project_1.domain.usecase.datastore.ClearSessionDataStoreUseCase
import com.example.project_1.presentation.event.home.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val clearSessionDataStoreUseCase: ClearSessionDataStoreUseCase
): ViewModel() {

    private val _homeNavigationEvent = MutableSharedFlow<HomeNavigationEvent>()
    val homeNavigationEvent: SharedFlow<HomeNavigationEvent> get() = _homeNavigationEvent

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnListButtonClicked -> navigateToList()
            is HomeEvent.Logout -> logout()
        }
    }
    private fun navigateToList() {
        viewModelScope.launch {
            _homeNavigationEvent.emit(HomeNavigationEvent.NavigateToList)
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            clearSessionDataStoreUseCase()
            _homeNavigationEvent.emit(HomeNavigationEvent.NavigateToWelcome)
        }
    }

    sealed interface HomeNavigationEvent {
        data object NavigateToList : HomeNavigationEvent
        data object NavigateToWelcome : HomeNavigationEvent
    }
}
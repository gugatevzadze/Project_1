package com.example.project_1.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.domain.usecase.auth.LogoutUseCase
import com.example.project_1.domain.usecase.datastore.ClearSessionDataStoreUseCase
import com.example.project_1.presentation.event.profile.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val clearSessionDataStoreUseCase: ClearSessionDataStoreUseCase
) : ViewModel() {

    private val _profileNavigationEvent = MutableSharedFlow<ProfileNavigationEvent>()
    val profileNavigationEvent: SharedFlow<ProfileNavigationEvent> get() = _profileNavigationEvent

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Logout -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            clearSessionDataStoreUseCase()
            _profileNavigationEvent.emit(ProfileNavigationEvent.NavigateToWelcome)
        }
    }

    sealed interface ProfileNavigationEvent {
        data object NavigateToWelcome : ProfileNavigationEvent
    }
}
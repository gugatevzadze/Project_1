package com.example.project_1.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.presentation.event.home.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _homeNavigationEvent = MutableSharedFlow<HomeNavigationEvent>()
    val homeNavigationEvent: SharedFlow<HomeNavigationEvent> get() = _homeNavigationEvent

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.onListButtonClicked -> navigateToList()
        }
    }
    private fun navigateToList() {
        viewModelScope.launch {
            _homeNavigationEvent.emit(HomeNavigationEvent.NavigateToList)
        }
    }

    sealed interface HomeNavigationEvent {
        data object NavigateToList : HomeNavigationEvent
    }
}
package com.example.project_1.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {
    private val _splashLoading = MutableStateFlow(true)
    val splashLoading = _splashLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _splashLoading.value = false
        }
    }
}
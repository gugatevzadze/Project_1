package com.example.project_1.presentation.event.profile

import com.example.project_1.presentation.event.list.ListEvent

sealed class ProfileEvent {
    data object Logout : ProfileEvent()
}

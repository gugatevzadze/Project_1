package com.example.project_1.presentation.screen.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.project_1.databinding.FragmentProfileBinding
import com.example.project_1.presentation.base.BaseFragment
import com.example.project_1.presentation.event.profile.ProfileEvent
import com.example.project_1.presentation.screen.list.ListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun setUp() {
    }

    override fun viewActionListeners() {
        logoutButtonClicked()
    }

    override fun bindObservers() {
        observeNavigationEvents()
    }

    private fun observeNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profileNavigationEvent.collect {
                    handleNavigationEvent(it)
                }
            }
        }
    }

    private fun handleLogout() {
        viewModel.onEvent(ProfileEvent.Logout)
    }

    private fun logoutButtonClicked() {
        binding.btnLogOut.setOnClickListener {
            handleLogout()
        }
    }

    private fun handleNavigationEvent(event: ProfileViewModel.ProfileNavigationEvent) {
        when (event) {
            is ProfileViewModel.ProfileNavigationEvent.NavigateToWelcome -> findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToWelcomeFragment())
        }
    }
}
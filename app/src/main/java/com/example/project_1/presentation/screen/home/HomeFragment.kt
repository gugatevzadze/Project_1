package com.example.project_1.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.project_1.databinding.FragmentHomeBinding
import com.example.project_1.presentation.base.BaseFragment
import com.example.project_1.presentation.event.home.HomeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setUp() {
    }

    override fun viewActionListeners() {
        onListButtonClicked()
        handleLogoutButtonClicked()
    }

    override fun bindObservers() {
        observeNavigationEvent()
    }

    private fun observeNavigationEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeNavigationEvent.collect { navigationEvent ->
                    handleNavigationEvent(navigationEvent)
                }
            }
        }
    }

    private fun onListButtonClicked() {
        binding.btnToList.setOnClickListener {
            handleListButtonClicked()
        }
    }

    private fun handleListButtonClicked() {
        viewModel.onEvent(HomeEvent.OnListButtonClicked)
    }

    private fun handleLogout() {
        viewModel.onEvent(HomeEvent.Logout)
    }

    private fun handleLogoutButtonClicked() {
        binding.btnLogOut.setOnClickListener {
            handleLogout()
        }
    }

    private fun handleNavigationEvent(event: HomeViewModel.HomeNavigationEvent) {
        when (event) {
            is HomeViewModel.HomeNavigationEvent.NavigateToList -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListFragment())
            is HomeViewModel.HomeNavigationEvent.NavigateToWelcome -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWelcomeFragment())
        }
    }
}
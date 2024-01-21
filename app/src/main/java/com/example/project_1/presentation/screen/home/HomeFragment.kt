package com.example.project_1.presentation.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
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
        viewModel.onEvent(HomeEvent.onListButtonClicked)
    }

    private fun handleNavigationEvent(event: HomeViewModel.HomeNavigationEvent) {
        when (event) {
            is HomeViewModel.HomeNavigationEvent.NavigateToList -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListFragment())
        }
    }
}
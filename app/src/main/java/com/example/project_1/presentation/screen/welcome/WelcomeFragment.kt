package com.example.project_1.presentation.screen.welcome

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.project_1.databinding.FragmentWelcomeBinding
import com.example.project_1.presentation.base.BaseFragment
import com.example.project_1.presentation.event.welcome.WelcomeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    private val viewModel: WelcomeViewModel by viewModels()

    override fun setUp() {
    }

    override fun viewActionListeners() {
        handleRegisterBtnClick()
        handleLoginBtnClick()
    }

    override fun bindObservers() {
        observeNavigationEvent()
    }

    private fun handleRegisterBtnClick() {
        binding.btnRegister.setOnClickListener {
            viewModel.onEvent(WelcomeEvent.RegisterButtonClicked)
        }
    }

    private fun handleLoginBtnClick() {
        binding.btnLogIn.setOnClickListener {
            viewModel.onEvent(WelcomeEvent.LoginButtonClicked)
        }

    }

    private fun observeNavigationEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.welcomeNavigationEvent.collect { navigationEvent ->
                    handleNavigationEvent(navigationEvent)
                }
            }
        }
    }

    private fun handleNavigationEvent(event: WelcomeViewModel.WelcomeNavigationEvent) {
        when (event) {
            is WelcomeViewModel.WelcomeNavigationEvent.NavigateToLogin -> findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToLogInFragment()
            )
            is WelcomeViewModel.WelcomeNavigationEvent.NavigateToRegister -> findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
            )
            is WelcomeViewModel.WelcomeNavigationEvent.NavigateToHome -> findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
            )
        }
    }
}
package com.example.project_1.presentation.screen.login

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.project_1.databinding.FragmentLogInBinding
import com.example.project_1.presentation.base.BaseFragment
import com.example.project_1.presentation.event.login.LogInEvent
import com.example.project_1.presentation.state.login.LogInState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()

    override fun setUp() {
    }

    override fun onClickListeners() {
        loginButtonClicked()
    }

    override fun bindObservers() {
        observeLoginState()
        observeNavigationEvents()
    }

    private fun observeLoginState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInState.collect {
                    handleLogInState(logInState = it)
                }
            }
        }
    }

    private fun observeNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginNavigationEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    private fun loginButtonClicked() {
        binding.btnLogIn.setOnClickListener {
            logIn()
        }
    }

    private fun logIn() {
        if (binding.cbRememberMe.isChecked)
            viewModel.onEvent(
                LogInEvent.LogInWithRememberMe(
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        else
            viewModel.onEvent(
                LogInEvent.LogIn(
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
    }

    private fun handleLogInState(logInState: LogInState) {
        binding.progressBar.isVisible = logInState.isLoading
        if (logInState.isLoading) View.VISIBLE else View.GONE

        logInState.errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(LogInEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: LogInViewModel.LogInNavigationEvent) {
        when (event) {
            is LogInViewModel.LogInNavigationEvent.NavigateToList ->
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToListFragment())
        }
    }
}
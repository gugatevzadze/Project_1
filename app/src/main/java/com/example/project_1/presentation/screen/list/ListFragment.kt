package com.example.project_1.presentation.screen.list

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_1.databinding.FragmentListBinding
import com.example.project_1.presentation.base.BaseFragment
import com.example.project_1.presentation.event.list.ListEvent
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.state.list.ListState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var listAdapter: ListRecyclerAdapter

    override fun setUp() {
        initRecyclerView()
    }

    override fun onClickListeners() {
        logoutButtonClicked()
    }

    override fun bindObservers() {
        observeNavigationEvents()
        observeListState()
    }

    private fun initRecyclerView() {
        listAdapter = ListRecyclerAdapter (
            onItemClick = {
                handleItemClick(it)
            }
        )
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listAdapter
        }
        viewModel.onEvent(ListEvent.GetPlantList)
    }

    private fun observeListState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listState.collect {
                    handleListState(state = it)
                }
            }
        }
    }

    private fun observeNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listNavigationEvent.collect {
                    handleNavigationEvent(it)
                }
            }
        }
    }

    private fun handleListState(state: ListState) {
        state.plants?.let {
            listAdapter.submitList(it)
        }
        state.errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        binding.progressBar.isVisible = state.isLoading
    }

    private fun handleItemClick(plant: PlantModel) {
        viewModel.onEvent(ListEvent.PlantItemClick(plant = plant))
    }
    private fun logout() {
        viewModel.onEvent(ListEvent.Logout)
    }

    private fun logoutButtonClicked() {
        binding.btnLogOut.setOnClickListener {
            logout()
        }
    }
    private fun handleNavigationEvent(event: ListViewModel.ListNavigationEvent) {
        when (event) {
            is ListViewModel.ListNavigationEvent.NavigateToWelcome -> findNavController().navigate(
                ListFragmentDirections.actionListFragmentToWelcomeFragment()
            )
            is ListViewModel.ListNavigationEvent.NavigateToDetail -> findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(event.plantId)
            )
        }
    }
}
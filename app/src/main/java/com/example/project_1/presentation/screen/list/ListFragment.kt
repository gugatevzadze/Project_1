package com.example.project_1.presentation.screen.list

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
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
import com.example.project_1.presentation.model.user.UserModel
import com.example.project_1.presentation.state.list.ListState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var listAdapter: ListRecyclerAdapter

    override fun setUp() {
        initRecyclerView()
    }

    override fun viewActionListeners() {
        searchListener()
    }

    override fun bindObservers() {
        observeNavigationEvents()
        observeListState()
    }

    private fun initRecyclerView() {
        listAdapter = ListRecyclerAdapter(
            onItemClick = {
                handleItemClick(it)
            },
            onItemSelect = {
                handleUserPlantFavoriteSelection(it)
            }
        )
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
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

    private fun handleSearch(query: String) {
        viewModel.onEvent(ListEvent.PlantSearch(query = query))
    }

    private fun searchListener() {
        binding.etSearch.addTextChangedListener {
            handleSearch(it.toString())
        }
    }

    private fun handleUserPlantFavoriteSelection(plant: PlantModel) {
        val firebaseUser = Firebase.auth.currentUser
        val userId = firebaseUser?.uid
        val user = UserModel(userId!!)
        viewModel.onEvent(ListEvent.AddPlantToFavorite(plant = plant, user = user))
        viewModel.onEvent(ListEvent.AddPlantToDatabase(plant = plant))
        viewModel.onEvent(ListEvent.AddUserToDatabase(user = user))

    }

    private fun handleNavigationEvent(event: ListViewModel.ListNavigationEvent) {
        when (event) {
            is ListViewModel.ListNavigationEvent.NavigateToDetail -> findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(event.plantId)
            )
        }
    }
}
package com.example.project_1.presentation.screen.favourites

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_1.databinding.FragmentFavouritesBinding
import com.example.project_1.presentation.base.BaseFragment
import com.example.project_1.presentation.event.favourites.FavouritesEvent
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.screen.list.ListRecyclerAdapter
import com.example.project_1.presentation.state.favourites.FavouritesState
import kotlinx.coroutines.launch
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {

    private val viewModel: FavouritesViewModel by viewModels()
    private lateinit var listAdapter: FavouritesRecyclerAdapter

    override fun setUp() {
        initRecyclerView()
    }

    override fun viewActionListeners() {
    }

    override fun bindObservers() {
        observeUserFavourites()
    }

    private fun initRecyclerView() {
        listAdapter = FavouritesRecyclerAdapter(
            onItemClick = {
                handleButtonClick(it)
            }
        )
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
        val user = Firebase.auth.currentUser
        val userId = user!!.uid
        Log.d("FavouritesFragment", "User id: $userId")
        viewModel.onEvent(FavouritesEvent.GetFavouritesList(userId = userId))
    }

    private fun observeUserFavourites() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouritePlant.collect {
                    handleFavouriteList(state = it)
                }
            }
        }
    }
    private fun handleButtonClick(plant: PlantModel) {
        viewModel.onEvent(FavouritesEvent.RemovePlantFromFavourite(plant = plant))
    }
    private fun handleFavouriteList(state: FavouritesState) {
        state.favourites?.let {
            Log.d("FavouritesFragment", "Submitting list to adapter: ${it.size}")
            listAdapter.submitList(it)
        }
    }
}
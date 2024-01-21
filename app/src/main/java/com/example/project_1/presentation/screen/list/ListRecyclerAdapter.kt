package com.example.project_1.presentation.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_1.databinding.ListItemPlantBinding
import com.example.project_1.presentation.model.list.PlantModel

class ListRecyclerAdapter (
    private val onItemClick: (PlantModel) -> Unit,
    private val onItemSelect: (PlantModel) -> Unit
) :
    ListAdapter<PlantModel, ListRecyclerAdapter.UserListViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ListItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind()
    }

    inner class UserListViewHolder(private val binding: ListItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var plant: PlantModel
        fun bind() {
            plant = currentList[adapterPosition]
            binding.apply {
                tvName.text = plant.name
                tvSpecies.text = plant.species
                tvDescription.text = plant.description
                Glide.with(ivImage.context)
                    .load(plant.image)
                    .into(ivImage)
            }

            itemView.setOnClickListener {
                onItemClick.invoke(plant)
            }
            binding.btnAddToFavourites.setOnClickListener {
                plant.isFavorite = !plant.isFavorite
                onItemSelect.invoke(plant)
            }
        }
    }
    private class UserListDiffCallback : DiffUtil.ItemCallback<PlantModel>() {
        override fun areItemsTheSame(oldItem: PlantModel, newItem: PlantModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantModel, newItem: PlantModel): Boolean {
            return oldItem == newItem
        }
    }
}
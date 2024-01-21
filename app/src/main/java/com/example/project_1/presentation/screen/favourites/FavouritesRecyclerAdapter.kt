package com.example.project_1.presentation.screen.favourites

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_1.databinding.ListItemFavouritesBinding
import com.example.project_1.presentation.model.list.PlantModel

class FavouritesRecyclerAdapter : ListAdapter<PlantModel, FavouritesRecyclerAdapter.FavouritesViewHolder>(FavouritesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val binding =
            ListItemFavouritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        Log.d("FavouritesRecyclerAdapter", "Binding plant at position: $position")
        holder.bind()
    }

    inner class FavouritesViewHolder(private val binding: ListItemFavouritesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var plant: PlantModel
        fun bind() {
            plant = currentList[adapterPosition]
            binding.apply {
                tvName.text = plant.name
                tvFamily.text = plant.family
                tvSpecies.text = plant.species
                tvWateringSchedule.text = plant.wateringSchedule
                tvSunlightRequirement.text = plant.sunlightRequirement
                tvGrowthHeight.text = plant.growthHeight
                tvBloomingSeason.text = plant.bloomingSeason
                tvDescription.text = plant.description
                Glide.with(ivImage.context)
                    .load(plant.image)
                    .into(ivImage)
            }
        }
    }
    private class FavouritesDiffCallback : DiffUtil.ItemCallback<PlantModel>() {
        override fun areItemsTheSame(oldItem: PlantModel, newItem: PlantModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlantModel, newItem: PlantModel): Boolean {
            return oldItem == newItem
        }
    }
}
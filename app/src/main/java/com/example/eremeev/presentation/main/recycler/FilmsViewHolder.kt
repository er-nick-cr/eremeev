package com.example.eremeev.presentation.main.recycler

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.eremeev.databinding.FilmItemBinding
import com.example.eremeev.domain.entity.Film

class FilmsViewHolder(
    private val binding: FilmItemBinding,
    private val onItemClickListener: (Int) -> Unit,
    private val onItemLongCLickListener: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }

        binding.root.setOnLongClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemLongCLickListener.invoke(position)
            return@setOnLongClickListener true
        }
    }

    fun bind(film: Film) {
        with(binding) {
            banner.load(film.posterUrl) {
                memoryCachePolicy(CachePolicy.ENABLED)
            }
            name.text = film.name
            genre.text = film.genre
            favorite.isVisible = film.isFavorite
        }
    }
}
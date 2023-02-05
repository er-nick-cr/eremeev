package com.example.eremeev.presentation.detailed.recycler

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.eremeev.R
import com.example.eremeev.databinding.DetailedFilmItemBinding
import com.example.eremeev.domain.entity.DetailedFilm

class DetailedFilmViewHolder(
    private val binding: DetailedFilmItemBinding,
    private val setSpannedText: (stringResource: Int, text: String, view: TextView) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(detailedFilm: DetailedFilm) {
        with(binding) {
            banner.load(detailedFilm.bannerUrl) {
                memoryCachePolicy(CachePolicy.ENABLED)
            }
            title.text = detailedFilm.title
            description.text = detailedFilm.description
            setSpannedText(R.string.genres, detailedFilm.genres, binding.genres)
            setSpannedText(R.string.countries, detailedFilm.countries, binding.countries)
        }
    }
}
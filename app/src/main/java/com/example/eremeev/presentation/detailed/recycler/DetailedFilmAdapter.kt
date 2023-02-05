package com.example.eremeev.presentation.detailed.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eremeev.databinding.DetailedFilmItemBinding
import com.example.eremeev.domain.entity.DetailedFilm

class DetailedFilmAdapter(
    private val setSpannedText: (stringResource: Int, text: String, view: TextView) -> Unit,
) : RecyclerView.Adapter<DetailedFilmViewHolder>() {

    var items: List<DetailedFilm> = emptyList()
        set(value) {
            val callback = DiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedFilmViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = DetailedFilmItemBinding.inflate(inflater, parent, false)
        return DetailedFilmViewHolder(binding, setSpannedText)
    }

    override fun onBindViewHolder(holder: DetailedFilmViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
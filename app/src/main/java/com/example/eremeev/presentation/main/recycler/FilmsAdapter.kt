package com.example.eremeev.presentation.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eremeev.databinding.FilmItemBinding
import com.example.eremeev.domain.entity.Film

class FilmsAdapter(
    private val onItemClickListener: (Film) -> Unit,
    private val onItemLongClickListener: (Film) -> Unit,
) : RecyclerView.Adapter<FilmsViewHolder>() {

    var items: List<Film> = emptyList()
        set(value) {
            val callback = DiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(inflater, parent, false)
        return FilmsViewHolder(binding, ::onItemClickListener, ::onItemLongClickListener)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])

    private fun onItemLongClickListener(position: Int) = onItemLongClickListener.invoke(items[position])
}
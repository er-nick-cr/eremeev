package com.example.eremeev.presentation.detailed.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.eremeev.domain.entity.DetailedFilm

class DiffCallback(
    private val oldList: List<DetailedFilm>,
    private val newList: List<DetailedFilm>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
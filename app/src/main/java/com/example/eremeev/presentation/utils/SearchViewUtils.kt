package com.example.eremeev.presentation.utils

import androidx.appcompat.widget.SearchView

inline fun SearchView.addQueryTextListener(
    crossinline onQueryTextSubmit: (query: String) -> Unit = { _ -> },
    crossinline onQueryTextChange: (query: String) -> Unit = { _ -> },
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onQueryTextSubmit(query)
            return false
        }

        override fun onQueryTextChange(query: String): Boolean {
            onQueryTextChange(query)
            return true
        }
    })
}
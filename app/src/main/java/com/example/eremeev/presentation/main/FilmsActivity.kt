package com.example.eremeev.presentation.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eremeev.R
import com.example.eremeev.databinding.ActivityFilmsBinding
import com.example.eremeev.domain.entity.Film
import com.example.eremeev.presentation.main.recycler.FilmsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsActivity : AppCompatActivity() {

    private val viewModel: FilmsViewModel by viewModel()
    private val binding: ActivityFilmsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        binding.error.button.setOnClickListener {
            repeatRequest()
        }

        val filmsAdapter = FilmsAdapter(::onFilmClick, ::onFilmLongClick)

        binding.toolbarTitle.text = getString(R.string.films_title_top)

        with(viewModel) {
            topFilmsLiveData.observe(this@FilmsActivity) { films -> filmsAdapter.items = films }
            isLoadingLiveData.observe(this@FilmsActivity) { isLoading -> showLoader(isLoading)}
            errorLiveData.observe(this@FilmsActivity) { setError() }
        }

        binding.recycler.adapter = filmsAdapter

        setToolbarSearchButton()
        viewModel.requestTopFilms()
    }

    private fun setToolbarSearchButton() {
        binding.toolbar.inflateMenu(R.menu.films_content_menu)
    }

    private fun onFilmClick(film: Film) {
        Toast.makeText(this, film.name, Toast.LENGTH_LONG).show()
    }

    private fun onFilmLongClick(film: Film) {
        viewModel.addToFavorite(film)
    }

    private fun setError() = with(binding) {
        recycler.isVisible = false
        error.root.isVisible = true
    }

    private fun repeatRequest() = with(binding) {
        recycler.isVisible = true
        error.root.isVisible = false
        viewModel.requestTopFilms()
    }

    private fun showLoader(isLoading: Boolean) = with(binding) {
        recycler.isVisible = !isLoading
        progressBar.isVisible = isLoading
    }
}
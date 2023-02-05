package com.example.eremeev.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eremeev.R
import com.example.eremeev.databinding.ActivityFilmsBinding
import com.example.eremeev.domain.entity.Film
import com.example.eremeev.presentation.detailed.DetailedFilmActivity
import com.example.eremeev.presentation.main.entity.FilmsTabs
import com.example.eremeev.presentation.main.recycler.FilmsAdapter
import com.example.eremeev.presentation.utils.addQueryTextListener
import com.example.eremeev.presentation.utils.toast
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsActivity : AppCompatActivity() {

    private val viewModel: FilmsViewModel by viewModel()
    private val binding: ActivityFilmsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        setOnClickListeners()

        val filmsAdapter = FilmsAdapter(::onFilmClick, ::onFilmLongClick)

        with(viewModel) {
            filmsLiveData.observe(this@FilmsActivity) { films -> filmsAdapter.items = films }
            errorLiveData.observe(this@FilmsActivity) { setError() }
            operationErrorLiveData.observe(this@FilmsActivity) { toast(R.string.database_error) }
            tabsLiveData.observe(this@FilmsActivity) { tab -> repeatRequest(tab) }

            isLoadingLiveData.observe(this@FilmsActivity) { isLoading ->
                binding.progressBar.isVisible = isLoading
                binding.recycler.isVisible = !isLoading
            }
        }

        binding.recycler.adapter = filmsAdapter

        setSupportActionBar(binding.toolbar)

        setToolbarComponents()
        disableButton(binding.topFilmsButton)
        viewModel.requestTopFilms()
    }

    private fun setToolbarComponents() = with(binding) {
        toolbar.inflateMenu(R.menu.films_search_menu)




        toolbar.title = getString(R.string.films_title_top)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.films_search_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)

        searchView.addQueryTextListener { searchValue -> viewModel.searchFilms(searchValue) }

        return super.onCreateOptionsMenu(menu)
    }

    private fun setOnClickListeners() = with(binding) {
        error.button.setOnClickListener { repeatRequest(viewModel.currentTab) }

        topFilmsButton.setOnClickListener {
            viewModel.openTopFilmsTab()
            enableButton(favoritesButton)
            disableButton(topFilmsButton)
        }

        favoritesButton.setOnClickListener {
            viewModel.openFavoriteFilmsTab()
            enableButton(topFilmsButton)
            disableButton(favoritesButton)
        }
    }

    private fun onFilmClick(film: Film) {
        val intent = Intent(this@FilmsActivity, DetailedFilmActivity::class.java)
        intent.putExtra("id", film.id)
        startActivity(intent)
    }

    private fun onFilmLongClick(film: Film) {
        viewModel.addOrDeleteFromFavorite(film.copy(isFavorite = !film.isFavorite))
    }

    private fun setError() = with(binding) {
        recycler.isVisible = false
        error.root.isVisible = true
    }

    private fun repeatRequest(currentTab: FilmsTabs) = with(binding) {
        recycler.isVisible = true
        error.root.isVisible = false
        when(currentTab) {
            is FilmsTabs.Top -> {
                viewModel.requestTopFilms()
                toolbar.title = getString(R.string.films_title_top)
            }
            is FilmsTabs.Favorites -> {
                viewModel.requestFavoriteFilms()
                toolbar.title = getString(R.string.films_title_favorite)
            }
        }
    }

    private fun enableButton(button: MaterialButton) {
        button.isEnabled = true
        button.setBackgroundColor(getColor(R.color.accent_primary))
        button.setTextColor(getColor(R.color.white))
    }

    private fun disableButton(button: MaterialButton) {
        button.isEnabled = false
        button.setBackgroundColor(getColor(R.color.accent_secondary))
        button.setTextColor(getColor(R.color.accent_primary))
    }
}
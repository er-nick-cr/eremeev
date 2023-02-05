package com.example.eremeev.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eremeev.domain.entity.Film
import com.example.eremeev.domain.repository.FilmsRepository
import com.example.eremeev.presentation.main.entity.FilmsTabs
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FilmsViewModel(
    private val repository: FilmsRepository
) : ViewModel() {

    val filmsLiveData = MutableLiveData<List<Film>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val operationErrorLiveData = MutableLiveData<Unit>()
    val errorLiveData = MutableLiveData<Unit>()
    val tabsLiveData = MutableLiveData<FilmsTabs>()
    var currentTab: FilmsTabs = FilmsTabs.Top
    private lateinit var loadedFilms: List<Film>
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestTopFilms() {
        isLoadingLiveData.postValue(true)
        disposable.add(
            repository.getTopFilms()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        loadedFilms = value
                        filmsLiveData.postValue(value)
                        isLoadingLiveData.postValue(false)
                    },
                    { error ->
                        errorLiveData.postValue(Unit)
                        error.printStackTrace()
                        isLoadingLiveData.postValue(false)
                    },
                )
        )
    }

    fun requestFavoriteFilms() {
        isLoadingLiveData.postValue(true)
        disposable.add(
            repository.getFavoriteFilms()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        loadedFilms = value
                        filmsLiveData.postValue(value)
                        isLoadingLiveData.postValue(false)
                    },
                    { error ->
                        errorLiveData.postValue(Unit)
                        error.printStackTrace()
                        isLoadingLiveData.postValue(false)
                    },
                )
        )
    }

    fun addOrDeleteFromFavorite(film: Film) {
        if (!film.isFavorite) return deleteFromFavorite(film.id)

        addToFavorite(film)
    }

    fun searchFilms(searchValue: String) {
        filmsLiveData.postValue(loadedFilms.filter {
            it.name.contains(
                searchValue,
                ignoreCase = true
            )
        })
    }

    fun openTopFilmsTab() {
        tabsLiveData.postValue(FilmsTabs.Top)
        currentTab = FilmsTabs.Top
    }

    fun openFavoriteFilmsTab() {
        tabsLiveData.postValue(FilmsTabs.Favorites)
        currentTab = FilmsTabs.Favorites
    }

    private fun addToFavorite(film: Film) {
        disposable.add(
            repository.addToFavorite(film)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { changeIsFavorite(film.id) },
                    { operationErrorLiveData.postValue(Unit) },
                )
        )
    }

    private fun deleteFromFavorite(id: Int) {
        disposable.add(
            repository.deleteFromFavorite(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { changeIsFavorite(id) },
                    { operationErrorLiveData.postValue(Unit) }
                )
        )
    }

    private fun changeIsFavorite(id: Int) {
        loadedFilms = loadedFilms.map { film ->
            if (film.id == id) {
                film.copy(isFavorite = !film.isFavorite)
            } else {
                film
            }
        }
        filmsLiveData.postValue(loadedFilms)
    }
}
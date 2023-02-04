package com.example.eremeev.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eremeev.domain.entity.Film
import com.example.eremeev.domain.repository.FilmsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FilmsViewModel(
    private val repository: FilmsRepository
) : ViewModel() {

    val topFilmsLiveData = MutableLiveData<List<Film>>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val addingCompletedLiveData = MutableLiveData<Unit>()
    val addingErrorLiveData = MutableLiveData<Unit>()
    val errorLiveData = MutableLiveData<Unit>()
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
                        topFilmsLiveData.postValue(value)
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

    fun addToFavorite(film: Film) {
        disposable.add(
            repository.addToFavorite(film)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        addingCompletedLiveData.postValue(Unit)
                        changeIsFavorite(film.id)

                    },
                    { addingErrorLiveData.postValue(Unit) },
                )
        )
    }

    private fun changeIsFavorite(id: Int) {
        loadedFilms = loadedFilms.map { film ->
            if (film.id == id) {
                film.copy(isFavorite = true)
            } else {
                film
            }
        }
        topFilmsLiveData.postValue(loadedFilms)
    }
}
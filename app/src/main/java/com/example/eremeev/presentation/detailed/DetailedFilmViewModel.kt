package com.example.eremeev.presentation.detailed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eremeev.domain.entity.DetailedFilm
import com.example.eremeev.domain.repository.FilmsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailedFilmViewModel(
    private val repository: FilmsRepository
) : ViewModel() {

    val detailedFilmLiveData = MutableLiveData<DetailedFilm>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Unit>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestDetailedFilm(id: Int) {
        isLoadingLiveData.postValue(true)
        disposable.add(
            repository.getDetailedFilm(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        detailedFilmLiveData.postValue(value)
                        isLoadingLiveData.postValue(false)
                    },
                    { error ->
                        errorLiveData.postValue(Unit)
                        isLoadingLiveData.postValue(false)
                        error.printStackTrace()
                    }
                )
        )
    }
}
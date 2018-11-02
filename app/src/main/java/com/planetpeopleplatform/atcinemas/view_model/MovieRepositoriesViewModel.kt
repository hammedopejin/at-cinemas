package com.planetpeopleplatform.atcinemas.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.planetpeopleplatform.atcinemas.data.MovieRepository
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.model.MovieResult

/**
 * ViewModel for the [MainActivityFragment] screen.
 * The ViewModel works with the [MovieRepository] to get the data.
 */
class MovieRepositoriesViewModel(repository: MovieRepository) : ViewModel() {

   private val movieResult: MovieResult = repository.fetch()

    val movies: LiveData<PagedList<Movie>> = movieResult.data

    val networkErrors: LiveData<String> = movieResult.networkErrors

    private val queryLiveData = MutableLiveData<String>()

    private val moviesSearchResult: LiveData<MovieResult> = Transformations.map(queryLiveData, {
        repository.search(it)
    })

    val moviesSearch: LiveData<PagedList<Movie>> = Transformations.switchMap(moviesSearchResult,
            { it -> it.data })

    /**
     * Search repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }

}
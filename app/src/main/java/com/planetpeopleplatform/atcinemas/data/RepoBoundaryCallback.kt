package com.planetpeopleplatform.atcinemas.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.planetpeopleplatform.atcinemas.api.Service
import com.planetpeopleplatform.atcinemas.api.fetchMovies
import com.planetpeopleplatform.atcinemas.db.MovieLocalCache
import com.planetpeopleplatform.atcinemas.model.Movie

class RepoBoundaryCallback(
        private val service: Service,
        private val cache: MovieLocalCache) : PagedList.BoundaryCallback<Movie>() {

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        requestAndSaveData()
    }

    // keep the last requested page.
    // When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        fetchMovies(service, lastRequestedPage, NETWORK_PAGE_SIZE, { movies ->
            cache.insert(movies, {
                lastRequestedPage++
                isRequestInProgress = false
            })
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
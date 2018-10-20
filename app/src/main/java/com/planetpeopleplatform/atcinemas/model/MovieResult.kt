package com.planetpeopleplatform.atcinemas.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * MovieResult from a request, which contains LiveData<List<Movie>> holding query data,
 * and a LiveData<String> of network error state.
 */
data class MovieResult(
        val data: LiveData<PagedList<Movie>>,
        val networkErrors: LiveData<String>
)
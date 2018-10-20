package com.planetpeopleplatform.atcinemas.data

import android.arch.paging.LivePagedListBuilder
import com.planetpeopleplatform.atcinemas.api.Service
import com.planetpeopleplatform.atcinemas.db.MovieLocalCache
import com.planetpeopleplatform.atcinemas.model.MovieResult

class MovieRepository(
        private val service: Service,
        private val cache: MovieLocalCache) {

    fun fetch(): MovieResult {


        // Get data source factory from the local cache
        val dataSourceFactory = cache.moviesQuery()

        // Construct the boundary callback
        val boundaryCallback = RepoBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return MovieResult(data, networkErrors)

    }


    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}
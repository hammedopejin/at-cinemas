package com.planetpeopleplatform.atcinemas.db

import android.arch.paging.DataSource
import android.util.Log
import com.planetpeopleplatform.atcinemas.model.Movie
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class MovieLocalCache(
        private val movieDao: MovieDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(movies: List<Movie>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("MovieLocalCache", "inserting ${movies.size} movies")
            movieDao.insert(movies)
            insertFinished()
        }
    }

    /**
     * Request all LiveData<List<Movie>> from the Dao.
     */
    fun moviesQuery(): DataSource.Factory<Int, Movie> {
        return movieDao.moviesQuery()
    }
}
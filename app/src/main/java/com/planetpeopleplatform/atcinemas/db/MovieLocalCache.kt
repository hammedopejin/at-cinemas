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
            Log.d("MovieLocalCache", "inserting ${movies.get(0).title} movies")
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

    /**
     * Request a LiveData<List<Repo>> from the Dao, based on a movie name.
     * @param name repository name
     */
    fun moviesByName(name: String): DataSource.Factory<Int, Movie> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return movieDao.moviesByName(query)
    }
}
package com.planetpeopleplatform.atcinemas.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.planetpeopleplatform.atcinemas.model.Movie

/**
 * Room data access object for accessing the [Movie] table.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Movie>)

    // Fetch all movies
    @Query("SELECT * FROM movies")
    fun reposQuery(): DataSource.Factory<Int, Movie>

}
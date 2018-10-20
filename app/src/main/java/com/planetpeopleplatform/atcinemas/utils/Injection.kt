package com.planetpeopleplatform.atcinemas.utils

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.planetpeopleplatform.atcinemas.api.Service
import com.planetpeopleplatform.atcinemas.data.MovieRepository
import com.planetpeopleplatform.atcinemas.db.MovieLocalCache
import com.planetpeopleplatform.atcinemas.db.MovieDatabase
import com.planetpeopleplatform.atcinemas.view_model.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [MovieLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): MovieLocalCache {
        val database = MovieDatabase.getInstance(context)
        return MovieLocalCache(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [MovieRepository] based on the [Service] and a
     * [MovieLocalCache]
     */
    private fun provideMovieRepository(context: Context): MovieRepository {
        return MovieRepository(Service.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideMovieRepository(context))
    }

}
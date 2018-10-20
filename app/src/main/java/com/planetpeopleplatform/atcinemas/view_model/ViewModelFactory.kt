package com.planetpeopleplatform.atcinemas.view_model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.planetpeopleplatform.atcinemas.data.MovieRepository

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieRepositoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieRepositoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
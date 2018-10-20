package com.planetpeopleplatform.atcinemas.api

import com.google.gson.annotations.SerializedName
import com.planetpeopleplatform.atcinemas.model.Movie

/**
 * Data class to hold repo responses from movieRepo API calls.
 */
data class MovieResponse(
        @SerializedName("total_results") val total: Int = 0,
        @SerializedName("results") val items: List<Movie> = emptyList(),
        val nextPage: Int? = null
)
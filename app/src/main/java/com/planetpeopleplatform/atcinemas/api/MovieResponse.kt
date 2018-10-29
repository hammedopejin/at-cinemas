package com.planetpeopleplatform.atcinemas.api

import com.google.gson.annotations.SerializedName
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Constants.RESEULTS

/**
 * Data class to hold movie responses from API calls.
 */
data class MovieResponse(
        @SerializedName("total_results") val total: Int = 0,
        @SerializedName(RESEULTS) val results: List<Movie> = emptyList(),
        val nextPage: Int? = null
)
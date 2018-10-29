package com.planetpeopleplatform.atcinemas.api

import com.google.gson.annotations.SerializedName
import com.planetpeopleplatform.atcinemas.model.Reviews

/**
 * Data class to hold reviews responses from API calls.
 */
data class ReviewsResponse(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("pages") val page: Int? = null,
        @SerializedName("results") val results: List<Reviews>? = null,
        @SerializedName("total_pages") val total_pages: Int? = null,
        @SerializedName("total_results") val total_results: Int? = null
)
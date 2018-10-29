package com.planetpeopleplatform.atcinemas.api

import com.google.gson.annotations.SerializedName
import com.planetpeopleplatform.atcinemas.model.Trailer

/**
 * Data class to hold trailer responses from API calls.
 */
data class TrailerResponse(
        @SerializedName("id") val id_trailer: Int? = null,
        @SerializedName("results") val results: List<Trailer>? = null
)
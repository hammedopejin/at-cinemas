package com.planetpeopleplatform.atcinemas.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for a TheMovieDB repo that holds all the information about a movie.
 * Objects of this type are received from the TheMovieDB API, therefore all the fields are annotated
 * with the serialized name.
 * This class also defines the Room repos table, where the repo [id] is the primary key.
 */
@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey @field:SerializedName("movie_id") val id: Long,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("overview") val description: String?,
        @field:SerializedName("poster_path") val url: String?,
        @field:SerializedName("user_rating") val rating: Double?,
        @field:SerializedName("release_date") val date: String?
)
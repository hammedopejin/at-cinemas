package com.planetpeopleplatform.atcinemas.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.planetpeopleplatform.atcinemas.utils.Constants.ID
import com.planetpeopleplatform.atcinemas.utils.Constants.OVERVIEW
import com.planetpeopleplatform.atcinemas.utils.Constants.POSTER_PATH
import com.planetpeopleplatform.atcinemas.utils.Constants.RELEASE_DATE
import com.planetpeopleplatform.atcinemas.utils.Constants.TITLE
import com.planetpeopleplatform.atcinemas.utils.Constants.VOTE_AVERAGE

/**
 * Immutable model class for a TheMovieDB repo that holds all the information about a movie.
 * Objects of this type are received from the TheMovieDB API, therefore all the fields are annotated
 * with the serialized name.
 * This class also defines the Room repos table, where the repo [id] is the primary key.
 */
@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey @field:SerializedName(ID) val id: Long,
        @field:SerializedName(TITLE) val title: String,
        @field:SerializedName(OVERVIEW) val description: String?,
        @field:SerializedName(POSTER_PATH) val url: String?,
        @field:SerializedName(VOTE_AVERAGE) val rating: Double?,
        @field:SerializedName(RELEASE_DATE) val date: String?
)
package com.planetpeopleplatform.atcinemas.api

import com.planetpeopleplatform.atcinemas.BuildConfig
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Constants.MOVIE_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Fetch movies based on a query.
 * Trigger a request to the TheMovieDB Repo API with the following params:
 * @param page request page index
 * @param itemsPerPage number of repositories to be returned by the TheMovieDB API per page
 *
 * The result of the request is handled by the implementation of the functions passed as params
 * @param onSuccess function that defines how to handle the list of repos received
 * @param onError function that defines how to handle request failure
 */
fun fetchMovies(
        service: Service,
        page: Int,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (error: String) -> Unit) {

    service.fetchMovies(page).enqueue(
            object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable) {
                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                        call: Call<MovieResponse>?,
                        response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val movies = response.body()?.results ?: emptyList()
                        onSuccess(movies)
                    } else {
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            }
    )
}

/**
 * TheMovieDB API communication setup via Retrofit.
 */
interface Service {

    @GET("now_playing?api_key=" + BuildConfig.MOVIEDB_API_KEY)
    fun fetchMovies(@Query("page") page: Int): Call<MovieResponse>

    @GET("{movie_id}/videos?api_key=" + BuildConfig.MOVIEDB_API_KEY)
    fun fetchMovieTrailer(@Path("movie_id") id: Long): Call<TrailerResponse>

    @GET("{movie_id}/reviews?api_key=" + BuildConfig.MOVIEDB_API_KEY)
    fun fetchMovieReviews(@Path("movie_id") id: Long): Call<ReviewsResponse>

    companion object {
        private const val BASE_URL = MOVIE_BASE_URL

        fun create(): Service {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Service::class.java)
        }
    }
}
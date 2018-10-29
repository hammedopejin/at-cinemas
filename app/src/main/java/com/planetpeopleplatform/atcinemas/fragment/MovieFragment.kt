package com.planetpeopleplatform.atcinemas.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.ReviewsAdapter
import com.planetpeopleplatform.atcinemas.adapter.TrailerAdapter
import com.planetpeopleplatform.atcinemas.api.ReviewsResponse
import com.planetpeopleplatform.atcinemas.api.Service
import com.planetpeopleplatform.atcinemas.api.TrailerResponse
import com.planetpeopleplatform.atcinemas.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private val KEY_MOVIE_ID = "com.planetpeopleplatform.atcinemas.key.movieID"
    private val KEY_MOVIE_TITLE = "com.planetpeopleplatform.atcinemas.key.movieTITLE"
    private val KEY_MOVIE_POSTER = "com.planetpeopleplatform.atcinemas.key.moviePoster"
    private val KEY_MOVIE_RATING = "com.planetpeopleplatform.atcinemas.key.movieRATING"
    private val KEY_MOVIE_RELEASE_DATE = "com.planetpeopleplatform.atcinemas.key.movieRELEASEDATE"
    private val KEY_MOVIE_RELEASE_OVERVIEW = "com.planetpeopleplatform.atcinemas.key.movieOVERVIEW"

    private var trailerRecyclerView: RecyclerView? = null
    private var reviewsRecyclerView: RecyclerView? = null
    private var mMoview_id: Long = 0
    private lateinit var mOverview: String
    private lateinit var mReleaseDate: String
    private var mRatingBar: Float = 0.0f

    fun newInstance(movieId: Long, posterUrl: String?, date: String?,
                    title: String?, overView: String,
                    rating: Double?): MovieFragment {
        val fragment = MovieFragment()
        val argument = Bundle()
        argument.putLong(KEY_MOVIE_ID, movieId)
        argument.putString(KEY_MOVIE_POSTER, posterUrl)
        argument.putString(KEY_MOVIE_RELEASE_DATE, date)
        argument.putString(KEY_MOVIE_TITLE, title)
        argument.putString(KEY_MOVIE_RELEASE_OVERVIEW, overView)
        if (rating != null) {
            argument.putDouble(KEY_MOVIE_RATING, rating)
        }
        fragment.setArguments(argument)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pager_item, container, false)
        val posterImageView: ImageView = view.findViewById(R.id.movie_poster_image_view)
        val collapsingToolbarLayout: CollapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar)
        val backArrow: ImageButton = view.findViewById(R.id.back_arrow)

        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"))
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsing_tool_bar_layout_textview)

        backArrow.setOnClickListener { activity!!.onBackPressed() }


        val arguments = arguments

        if (arguments == null) {
            return null
        }
        val posterUrl = arguments.getString(KEY_MOVIE_POSTER)
        val date = arguments.getString(KEY_MOVIE_RELEASE_DATE)
        val title = arguments.getString(KEY_MOVIE_TITLE)
        val overView = arguments.getString(KEY_MOVIE_RELEASE_OVERVIEW)
        val rating = arguments.getDouble(KEY_MOVIE_RATING, 0.0)
        mMoview_id = arguments.getLong(KEY_MOVIE_ID)

        collapsingToolbarLayout.setTitle(title)
        Glide.with(container!!.context).load(Constants.THUMBNAIL_URL + posterUrl)
                .apply(RequestOptions.centerInsideTransform()
                .placeholder(R.drawable.ic_movie_creation_black_24dp)).into(posterImageView)
        mOverview = overView
        mReleaseDate = date
        mRatingBar = rating.toFloat()

        initTrailer(view)
        initReviews(view)
        return view
    }

    private fun initTrailer(view: View) {
        trailerRecyclerView = view.findViewById<View>(R.id.trailer_recycler_view) as RecyclerView
        loadTrailer()
    }

    private fun loadTrailer() {
        try {
            val apiService = Service.create()
            val call = apiService.fetchMovieTrailer(mMoview_id)
            call.enqueue(object : Callback<TrailerResponse> {
                override fun onResponse(call: Call<TrailerResponse>, response: Response<TrailerResponse>) {
                    val trailer = response.body()!!.results
                    trailerRecyclerView!!.setAdapter(TrailerAdapter(trailer!!))
                }

                override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
                    Log.d("Error", t.message)
                    Toast.makeText(context, "Error fetching trailer data", Toast.LENGTH_SHORT).show()

                }
            })

        } catch (e: Exception) {
            Log.d("Error", e.message)
            Toast.makeText(context, "Error fetching trailer data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initReviews(view: View) {
        reviewsRecyclerView = view.findViewById<View>(R.id.review_recycler_view) as RecyclerView
        loadReviews()
    }

    private fun loadReviews() {

        try {
            val apiService = Service.create()
            val call = apiService.fetchMovieReviews(mMoview_id)
            call.enqueue(object : Callback<ReviewsResponse> {
                override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
                    val reviews = response.body()!!.results
                    reviewsRecyclerView!!.setAdapter(ReviewsAdapter(reviews!!, mOverview,
                            mReleaseDate, mRatingBar))
                }

                override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                    Log.d("Error", t.message)
                    Toast.makeText(context, "Error fetching reviews data", Toast.LENGTH_SHORT).show()

                }
            })

        } catch (e: Exception) {
            Log.d("Error", e.message)
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}
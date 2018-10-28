package com.planetpeopleplatform.atcinemas.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.ReviewsAdapter
import com.planetpeopleplatform.atcinemas.adapter.TrailerAdapter
import com.planetpeopleplatform.atcinemas.model.Reviews
import com.planetpeopleplatform.atcinemas.model.Trailer
import com.planetpeopleplatform.atcinemas.utils.Constants
import java.util.ArrayList

class MovieFragment : Fragment() {

    private val KEY_MOVIE_TITLE = "com.planetpeopleplatform.atcinemas.key.movieTITLE"
    private val KEY_MOVIE_POSTER = "com.planetpeopleplatform.atcinemas.key.moviePoster"
    private val KEY_MOVIE_RATING = "com.planetpeopleplatform.atcinemas.key.movieRATING"
    private val KEY_MOVIE_RELEASE_DATE = "com.planetpeopleplatform.atcinemas.key.movieRELEASEDATE"

    private var trailerRecyclerView: RecyclerView? = null
    private var reviewsRecyclerView: RecyclerView? = null
    private var trailerAdapter: TrailerAdapter? = null
    private var trailerList: List<Trailer>? = null
    private var reviewsAdapter: ReviewsAdapter? = null
    private var reviewsList: List<Reviews>? = null

    fun newInstance(posterUrl: String, date: String, title: String): MovieFragment {
        val fragment = MovieFragment()
        val argument = Bundle()
        argument.putString(KEY_MOVIE_POSTER, posterUrl)
        argument.putString(KEY_MOVIE_RELEASE_DATE, date)
        argument.putString(KEY_MOVIE_TITLE, title)
        fragment.setArguments(argument)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pager_item, container, false)
        val mPosterImageView: ImageView = view.findViewById(R.id.movie_poster_image_view)
        val mRatingBar: RatingBar = view.findViewById(R.id.rb_rating)
        val mReleaseDate: TextView = view.findViewById(R.id.release_date_text_view)
        val mCollapsingToolbarLayout: CollapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar)
        val mBackArrow: ImageButton = view.findViewById(R.id.back_arrow)

        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"))
        mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsing_tool_bar_layout_textview)

        mBackArrow.setOnClickListener { activity!!.onBackPressed() }


        val arguments = arguments

        if (arguments == null) {
            return null
        }
        val posterUrl = arguments.getString(KEY_MOVIE_POSTER)
        val date = arguments.getString(KEY_MOVIE_RELEASE_DATE)
        val title = arguments.getString(KEY_MOVIE_TITLE)

        mCollapsingToolbarLayout.setTitle(title)

        Glide.with(container!!.context).load(Constants.THUMBNAIL_URL + posterUrl)
                .apply(RequestOptions.centerInsideTransform()
                .placeholder(R.drawable.ic_movie_creation_black_24dp)).into(mPosterImageView)
        mReleaseDate.text = date

        initTrailer(view)
        initReviews(view)
        return view
    }

    private fun initTrailer(view: View) {
        trailerList = ArrayList<Trailer>()
        trailerAdapter = TrailerAdapter(trailerList as ArrayList<Trailer>)

        trailerRecyclerView = view.findViewById<View>(R.id.trailer_recycler_view) as RecyclerView

        val mLayoutManager = LinearLayoutManager(context)
        trailerRecyclerView!!.setLayoutManager(mLayoutManager)
        val trailerLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        trailerRecyclerView!!.setLayoutManager(trailerLayoutManager)
        trailerRecyclerView!!.setAdapter(trailerAdapter)
        trailerAdapter!!.notifyDataSetChanged()
    }

    private fun initReviews(view: View) {
        reviewsList = ArrayList<Reviews>()
        reviewsAdapter = ReviewsAdapter(reviewsList as ArrayList<Reviews>)

        reviewsRecyclerView = view.findViewById<View>(R.id.review_recycler_view) as RecyclerView
        val reviewLayoutManager = LinearLayoutManager(context)
        reviewsRecyclerView!!.setLayoutManager(reviewLayoutManager)
        reviewsRecyclerView!!.setAdapter(reviewsAdapter)
        reviewsAdapter!!.notifyDataSetChanged()
    }
}
package com.planetpeopleplatform.atcinemas.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.utils.Constants

class MovieFragment : Fragment() {

    private val KEY_MOVIE_ID = "com.planetpeopleplatform.atcinemas.key.movieID"
    private val KEY_MOVIE_POSTER = "com.planetpeopleplatform.atcinemas.key.moviePoster"
    private val KEY_MOVIE_RATING = "com.planetpeopleplatform.atcinemas.key.movieRATING"
    private val KEY_MOVIE_RELEASE_DATE = "com.planetpeopleplatform.atcinemas.key.movieRELEASEDATE"

    fun newInstance(url: String, date: String): MovieFragment {
        val fragment = MovieFragment()
        val argument = Bundle()
        argument.putString(KEY_MOVIE_POSTER, url)
        argument.putString(KEY_MOVIE_RELEASE_DATE, date)
        fragment.setArguments(argument)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pager_item, container, false)
        val mPosterImageView: ImageView = view.findViewById(R.id.movie_poster_image_view)
        val mRatingBar: RatingBar = view.findViewById(R.id.rb_rating)
        val mReleaseDate: TextView = view.findViewById(R.id.release_date_text_view)


        val arguments = arguments

        if (arguments == null) {
            return null
        }
        val url = arguments.getString(KEY_MOVIE_POSTER)
        val date = arguments.getString(KEY_MOVIE_RELEASE_DATE)

        Glide.with(container!!.context).load(Constants.THUMBNAIL_URL + url)
                .apply(RequestOptions.centerInsideTransform()
                .placeholder(R.drawable.ic_movie_creation_black_24dp)).into(mPosterImageView)
        mReleaseDate.text = date


        return view
    }
}
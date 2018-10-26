package com.planetpeopleplatform.atcinemas.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerInsideTransform
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.activity.MovieDetailActivity
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Constants.MOVIE_ID
import com.planetpeopleplatform.atcinemas.utils.Constants.MOVIE_POSITION
import com.planetpeopleplatform.atcinemas.utils.Constants.THUMBNAIL_URL

/**
 * View Holder for a [Movie] RecyclerView grid item.
 */
class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mUrl: ImageView = view.findViewById(R.id.movie_image)
    private val mTitle: TextView = view.findViewById(R.id.movie_title)
    private var mMovie: Movie? = null


    init {
        view.setOnClickListener {
            val intent = Intent(mContext, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_POSITION, adapterPosition)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val bundle: Bundle = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity?).toBundle()
                mContext.startActivity(intent, bundle)
            } else {
                mContext.startActivity(intent)
            }
        }
    }



    fun bind(movie: Movie?) {
        if (movie == null) {
            val resources = itemView.resources
            mTitle.text = resources.getString(R.string.loading)

        } else {
            showMovieData(movie)
        }
    }


    private fun showMovieData(movie: Movie) {
        this.mMovie = movie
        mTitle.text = movie.title

        Glide.with(mContext).load(THUMBNAIL_URL + mMovie!!.url).apply(centerInsideTransform()
                .placeholder(R.drawable.ic_movie_creation_black_24dp)).into(mUrl)

    }

    companion object {
        lateinit var mContext: Context
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_view_item, parent, false)
            mContext = parent.context
            return MovieViewHolder(view)
        }
    }
}
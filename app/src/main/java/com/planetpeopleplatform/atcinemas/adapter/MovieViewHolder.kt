package com.planetpeopleplatform.atcinemas.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerInsideTransform
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Constants.THUMBNAIL_URL

/**
 * View Holder for a [Movie] RecyclerView grid item.
 */
class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mUrl: ImageView = view.findViewById(R.id.movie_image)
    private val mTitle: TextView = view.findViewById(R.id.movie_title)
    private var mMovie: Movie? = null



    fun bind(movie: Movie?) {
        if (movie == null) {
            val resources = itemView.resources
            mTitle.text = resources.getString(R.string.loading)

        } else {
            showRepoData(movie)
        }
    }

    private fun showRepoData(movie: Movie) {
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
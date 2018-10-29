package com.planetpeopleplatform.atcinemas.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.model.Reviews

class ReviewsAdapter(private val reviewsList: List<Reviews>?, private val overview: String,
                     private val releaseDate: String, private val ratingBar: Float)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val VIEW_TYPE_REVIEW = 1
    private val VIEW_TYPE_INFO_MOVIE_DETAIL = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{



        val view: View

        if (viewType == VIEW_TYPE_REVIEW) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.movie_review_item,
                    parent, false)
            return ReviewsViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.movie_info_item,
                    parent, false)
            return MovieInfoViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            VIEW_TYPE_REVIEW -> {
                (holder as ReviewsViewHolder).content.setText(reviewsList!![position].content)
                holder.reviewAuthor.setText(reviewsList[position].author)
            }
            VIEW_TYPE_INFO_MOVIE_DETAIL -> {
                (holder as MovieInfoViewHolder).overview.setText(overview)
                holder.releaseDate.setText(releaseDate)
                holder.ratingBar?.rating = ratingBar
            }
        }
    }

    override fun getItemCount(): Int {
        return reviewsList?.size ?: 0
    }

    inner class ReviewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var content: TextView
        var reviewAuthor: TextView

        init {
            content = view.findViewById<View>(R.id.review_content) as TextView
            reviewAuthor = view.findViewById<View>(R.id.review_author) as TextView
        }

    }

    inner class MovieInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ratingBar: RatingBar? = null
        var releaseDate: TextView
        var overview: TextView

        init {
            ratingBar = view.findViewById(R.id.rb_rating)
            releaseDate = view.findViewById(R.id.release_date_text_view)
            overview = view.findViewById(R.id.overview_tv)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position < 1){
            return VIEW_TYPE_INFO_MOVIE_DETAIL
        } else {
            return VIEW_TYPE_REVIEW
        }
    }
}
package com.planetpeopleplatform.atcinemas.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.model.Reviews

class ReviewsAdapter(private val reviewsList: List<Reviews>?)
    : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_review_item,
                parent, false)
        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.content.setText(reviewsList!![position].getContent())
        holder.reviewAuthor.setText(reviewsList!![position].getAuthor())
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
}
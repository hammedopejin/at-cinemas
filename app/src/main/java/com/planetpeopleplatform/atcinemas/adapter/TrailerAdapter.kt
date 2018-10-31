package com.planetpeopleplatform.atcinemas.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.model.Trailer

class TrailerAdapter(private val trailerList: List<Trailer>?)
    : RecyclerView.Adapter<TrailerAdapter.TrailerMyViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerAdapter.TrailerMyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_trailer_item, parent,
                false)
        mContext = parent.context
        return TrailerMyViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerMyViewHolder, position: Int) {
        val videoClip = "http://img.youtube.com/vi/" + trailerList?.get(position)?.key + "/0.jpg"

        Glide.with(mContext)
                .load(videoClip)
                .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        if (trailerList != null) {
            return trailerList.size
        }
        return 0;
    }

    inner class TrailerMyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var thumbnail: ImageView

        init {
            thumbnail = view.findViewById<View>(R.id.thumbnail) as ImageView

            view.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val videoId = trailerList?.get(pos)?.key
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("movie_id", videoId)
                    mContext.startActivity(intent)
                }
            }

        }

    }
}
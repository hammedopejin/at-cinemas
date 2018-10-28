package com.planetpeopleplatform.atcinemas.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.model.Trailer

class TrailerAdapter(private val trailerList: List<Trailer>)
    : RecyclerView.Adapter<TrailerAdapter.TrailerMyViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerAdapter.TrailerMyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_trailer_item, parent,
                false)
        mContext = parent.context
        return TrailerMyViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerMyViewHolder, position: Int) {
        val videoClip = "http://img.youtube.com/vi/" + trailerList[position].getKey() + "/0.jpg"

        Glide.with(mContext)
                .load(videoClip)
                .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    inner class TrailerMyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var thumbnail: ImageView

        init {
            thumbnail = view.findViewById<View>(R.id.thumbnail) as ImageView

            view.setOnClickListener { v ->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem = trailerList[pos]
                    val videoId = trailerList[pos].getKey()
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("movie_id", videoId)
                    mContext.startActivity(intent)

                    Toast.makeText(v.context, "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}
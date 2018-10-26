package com.planetpeopleplatform.atcinemas.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.fragment.MoviePagerFragment
import com.planetpeopleplatform.atcinemas.utils.Constants.MOVIE_ID

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val arguments = intent
        var movieId: String

        if (intent == null) {
            return
        }
        if (arguments != null) {
            movieId = intent!!.getStringExtra(MOVIE_ID)
            val fragmentManager = supportFragmentManager
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, MoviePagerFragment().newInstance(movieId),
                            MoviePagerFragment::class.java.getSimpleName())
                    .commit()
        }
    }
}
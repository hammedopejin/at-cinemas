package com.planetpeopleplatform.atcinemas.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.fragment.MainActivityFragment.Companion.moviePosition
import com.planetpeopleplatform.atcinemas.fragment.MoviePagerFragment
import com.planetpeopleplatform.atcinemas.utils.Constants.MOVIE_POSITION

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        if (savedInstanceState != null){
            moviePosition = savedInstanceState.getInt(MOVIE_POSITION, 0)
        } else {
            if (intent == null) {
                return
            }

            moviePosition = intent!!.getIntExtra(MOVIE_POSITION, 0)

        }

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container, MoviePagerFragment().newInstance(moviePosition),
                        MoviePagerFragment::class.java.getSimpleName()).commit()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MOVIE_POSITION, moviePosition)
        super.onSaveInstanceState(outState)
    }
}
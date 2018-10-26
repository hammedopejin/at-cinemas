package com.planetpeopleplatform.atcinemas.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.MoviePagerAdapter

/**
 * A fragment for displaying a pager of movies.
 */
class MoviePagerFragment : Fragment() {

    private val KEY_MOVIE_ID = "com.planetpeopleplatform.atcinemas.key.movieID"

    fun newInstance(movieId: String): MoviePagerFragment {
        val fragment = MoviePagerFragment()
        val argument = Bundle()
        argument.putString(KEY_MOVIE_ID, movieId)
        fragment.setArguments(argument)
        return fragment
    }

    private var viewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewPager = inflater.inflate(R.layout.fragment_movie_pager, container, false) as ViewPager


        val arguments = arguments

        if (arguments == null) {
            return null
        }

        val movieId= arguments.getString(KEY_MOVIE_ID)

        viewPager!!.adapter = MoviePagerAdapter(this.fragmentManager)


        return viewPager
    }
}
package com.planetpeopleplatform.atcinemas.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.planetpeopleplatform.atcinemas.R

class MovieFragment : Fragment() {

    private val KEY_MOVIE_ID = "com.planetpeopleplatform.atcinemas.key.movieID"

    fun newInstance(movieId: String): MovieFragment {
        val fragment = MovieFragment()
        val argument = Bundle()
        argument.putString(KEY_MOVIE_ID, movieId)
        fragment.setArguments(argument)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pager_item, container, false)



        return view
    }
}
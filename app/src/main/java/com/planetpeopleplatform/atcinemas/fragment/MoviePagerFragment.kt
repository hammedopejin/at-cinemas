package com.planetpeopleplatform.atcinemas.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.MoviePagerAdapter
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Injection
import com.planetpeopleplatform.atcinemas.view_model.MovieRepositoriesViewModel

/**
 * A fragment for displaying a pager of movies.
 */
class MoviePagerFragment : Fragment() {

    private val KEY_MOVIE_POSITION = "com.planetpeopleplatform.atcinemas.key.moviePosition"
    private lateinit var viewModel: MovieRepositoriesViewModel
    private lateinit var mViewPageAdapter: MoviePagerAdapter



    fun newInstance(moviePosition: Int): MoviePagerFragment {
        val fragment = MoviePagerFragment()
        val argument = Bundle()
        argument.putInt(KEY_MOVIE_POSITION, moviePosition)
        fragment.setArguments(argument)
        return fragment
    }

    private var viewPager: ViewPager? = null
    private var mMoviePosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewPager = inflater.inflate(R.layout.fragment_movie_pager, container, false) as ViewPager
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(context!!))
                .get(MovieRepositoriesViewModel::class.java)

        val arguments = arguments

        if (arguments == null) {
            return null
        }
        mMoviePosition = arguments.getInt(KEY_MOVIE_POSITION)
        initMovies()

        return viewPager
    }

    private fun initMovies() {
        viewModel.movies.observe(this, Observer<PagedList<Movie>> {
            Log.d("Activity", "movies_rv: ${it?.size}")
            Log.d("Activity", "movies_rv: " + mMoviePosition)
            Log.d("Activity", "movies_rv: ${it?.get(mMoviePosition)?.title}")
            mViewPageAdapter = MoviePagerAdapter(this, it)
            viewPager!!.adapter = mViewPageAdapter
            viewPager!!.setCurrentItem(mMoviePosition)

        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()

        })
    }
}
package com.planetpeopleplatform.atcinemas.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.MoviePagerAdapter
import com.planetpeopleplatform.atcinemas.fragment.MainActivityFragment.Companion.moviePosition
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Injection
import com.planetpeopleplatform.atcinemas.view_model.MovieRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * A fragment for displaying a pager of movies.
 */
class MoviePagerFragment : Fragment(), ViewPager.OnPageChangeListener {

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

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        mMoviePosition = position
        moviePosition = position
    }

    override fun onPageSelected(position: Int) {
    }

    private fun initMovies() {
        viewModel.movies.observe(this, Observer<PagedList<Movie>> {

            mViewPageAdapter = MoviePagerAdapter(this, it)
            viewPager!!.adapter = mViewPageAdapter
            viewPager!!.setCurrentItem(mMoviePosition)
            viewPager!!.addOnPageChangeListener(this)

        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Snackbar.make(container, "Network error!", Snackbar.LENGTH_SHORT).show()

        })
    }
}
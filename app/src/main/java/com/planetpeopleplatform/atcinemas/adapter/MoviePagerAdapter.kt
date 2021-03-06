package com.planetpeopleplatform.atcinemas.adapter

import android.arch.paging.PagedList
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.planetpeopleplatform.atcinemas.fragment.MovieFragment
import com.planetpeopleplatform.atcinemas.model.Movie

class MoviePagerAdapter(fragment: Fragment, movies: PagedList<Movie>?)
    : FragmentStatePagerAdapter(fragment.childFragmentManager) {

    private var mMovies: PagedList<Movie>? = movies

    override fun getCount(): Int {
        if (mMovies != null) {
            return mMovies!!.size
        }
        return 0
    }

    override fun getItem(position: Int): Fragment? {
        if (mMovies?.get(position)?.id != null) {
            return MovieFragment().newInstance(mMovies!![position]?.id!!,
                    mMovies!![position]?.url,
                    mMovies!![position]?.date,
                    mMovies!![position]?.title,
                    mMovies!![position]?.description!!,
                    mMovies!![position]?.rating)
        }
        return null
    }
}
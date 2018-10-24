package com.planetpeopleplatform.atcinemas.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.MoviesAdapter
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Injection
import com.planetpeopleplatform.atcinemas.view_model.MovieRepositoriesViewModel




class MainActivityFragment : Fragment() {
    private lateinit var viewModel: MovieRepositoriesViewModel
    private val adapter = MoviesAdapter()
    lateinit var mRv: RecyclerView
    lateinit var mPb: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main_activity, container, false)
        mRv = rootView.findViewById(R.id.movies_rv)
        mPb = rootView.findViewById(R.id.pb_loading_indicator)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get the view model
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(context!!))
                .get(MovieRepositoriesViewModel::class.java)

        initAdapter()
    }


    private fun initAdapter() {
        if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //portraitView
            mRv.layoutManager = GridLayoutManager(mRv.context, 2)
        } else {
            //landscapeView
            mRv.layoutManager = GridLayoutManager(mRv.context, 3)
        }
        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRv.addItemDecoration(decoration)
        mRv.adapter = adapter
        showLoading()

        viewModel.movies.observe(this, Observer<PagedList<Movie>> {
            Log.d("Activity", "movies_rv: ${it?.size}")
            Log.d("Activity", "movies_rv: ${it?.get(0)?.title}")
            adapter.submitList(it)
            showDataView()
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
            showDataView()
        })
    }

    private fun showDataView() {
        /* First, hide the loading indicator */
        mPb.visibility = View.INVISIBLE
        /* Finally, make sure the data is visible */
        mRv.visibility = View.VISIBLE
    }

    private fun showLoading() {
        /* Then, hide the data */
        mRv.visibility = View.INVISIBLE
        /* Finally, show the loading indicator */
        mPb.visibility = View.VISIBLE
    }
}
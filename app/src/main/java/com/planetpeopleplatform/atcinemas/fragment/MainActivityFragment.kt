package com.planetpeopleplatform.atcinemas.fragment

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.adapter.MoviesAdapter
import com.planetpeopleplatform.atcinemas.model.Movie
import com.planetpeopleplatform.atcinemas.utils.Injection
import com.planetpeopleplatform.atcinemas.view_model.MovieRepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_main_activity.*


class MainActivityFragment : Fragment() {
    private lateinit var viewModel: MovieRepositoriesViewModel
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main_activity, container, false)

        val searchView: SearchView = rootView.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return searchFeed(query)
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return searchFeed(newText)
            }
        })

        return rootView
    }

    override fun onResume() {
        super.onResume()
        movies_rv.smoothScrollToPosition(moviePosition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get the view model
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(context!!))
                .get(MovieRepositoriesViewModel::class.java)

        initAdapter()
    }


    private fun initAdapter() {

        movies_rv.layoutManager = GridLayoutManager(movies_rv.context, getResources().getInteger(R.integer.grid_span_count))

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        movies_rv.addItemDecoration(decoration)
        movies_rv.adapter = adapter
        showLoading()

        viewModel.movies.observe(this, Observer<PagedList<Movie>> {
            adapter.submitList(it)
            showDataView()
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            showDataView()
            Snackbar.make(fragment_container, "Network error!", Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun searchFeed(newText: String?): Boolean {
        if (newText != null && newText.length > 0) {
            filter(newText)
        } else {
            initAdapter()
        }
        return true
    }

    private fun filter(text: String) {

        viewModel.moviesSearch.observe(this, Observer<PagedList<Movie>> {
            adapter.submitList(it)
        })
        viewModel.searchRepo(text)
        adapter.submitList(null)

    }

    private fun showDataView() {
        /* First, hide the loading indicator */
        pb_loading_indicator.visibility = View.INVISIBLE
        /* Finally, make sure the data is visible */
        movies_rv.visibility = View.VISIBLE
    }

    private fun showLoading() {
        /* Then, hide the data */
        movies_rv.visibility = View.INVISIBLE
        /* Finally, show the loading indicator */
        pb_loading_indicator.visibility = View.VISIBLE
    }

    companion object {
        var moviePosition: Int = 0
    }
}
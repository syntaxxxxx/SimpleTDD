package com.learn.tdd.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.tdd.R
import com.learn.tdd.data.entity.TrendingRepo
import com.learn.tdd.data.network.NetworkListener
import com.learn.tdd.util.ViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.no_internet_layout.*
import kotlinx.android.synthetic.main.shimmer_layout.*

class MainFragment : Fragment(), NetworkListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeToRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(activity!!)
        viewModel.networkListener = this
        viewModel.getRepos().observe(this, Observer {
            showList(it)
        })
        loadData()
    }

    private fun loadData() {
        startShimmer()
        viewModel.getTrendingRepos()
    }

    private fun startShimmer() {
        rvRepoList.visibility = View.GONE
        shimmeringLayout.visibility = View.VISIBLE
        shimmeringLayout.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        stopShimmer()
    }

    private fun stopShimmer() {
        rvRepoList.visibility = View.VISIBLE
        shimmeringLayout.stopShimmer()
        shimmeringLayout.visibility = View.GONE
    }

    private fun showList(it: List<TrendingRepo>) {
        rvRepoList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RepoAdapter(it)
        }
    }

    override fun onSuccess() {
        stopShimmer()
        swipeToRefreshLayout.isRefreshing = false
    }

    override fun onFailure() {
        showNoInternetConnectionLayout()
        stopShimmer()
        swipeToRefreshLayout.isRefreshing = false
    }

    private fun showNoInternetConnectionLayout() {
        noInternetLayout.visibility = View.VISIBLE
        retryButton.setOnClickListener {
            noInternetLayout.visibility = View.GONE
            loadData()
        }
    }

    fun sortByStars() {
        viewModel.sortByStar()
    }

    fun sortByName() {
        viewModel.sortByName()
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory(activity.application)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

}

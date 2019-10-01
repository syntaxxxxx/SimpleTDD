package com.learn.tdd.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.learn.tdd.data.entity.TrendingRepo
import com.learn.tdd.data.network.NetworkListener
import com.learn.tdd.data.repository.RepoRepository

class MainViewModel(private val repoRepository: RepoRepository) : ViewModel() {

    var networkListener: NetworkListener? = null

    fun getRepos(): LiveData<List<TrendingRepo>> {
        return RepoRepository.trendingRepos
    }

    fun getTrendingRepos() {
        repoRepository.getRepos(networkListener)
    }

    fun sortByName() {
        repoRepository.sortByName()
    }

    fun sortByStar() {
        repoRepository.sortByStars()
    }


}

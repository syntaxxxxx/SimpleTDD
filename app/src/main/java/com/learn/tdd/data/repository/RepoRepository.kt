package com.learn.tdd.data.repository

import androidx.lifecycle.MutableLiveData
import com.learn.tdd.data.entity.TrendingRepo
import com.learn.tdd.data.network.Api
import com.learn.tdd.data.network.NetworkListener
import com.learn.tdd.data.room.TrendingRepoDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    companion object {

        val trendingRepos = MutableLiveData<List<TrendingRepo>>()
        lateinit var dao: TrendingRepoDao

        @Volatile
        private var instance: RepoRepository? = null
        private val LOCK = Any()

        operator fun invoke(trendingRepoDao: TrendingRepoDao) = instance ?: synchronized(LOCK) {
            instance ?: getInstance().also {
                instance = it
                dao = trendingRepoDao
            }
        }

        private fun getInstance() = RepoRepository()

    }

    fun getRepos(networkListener: NetworkListener?) {
        val localRepos = runBlocking {
            dao.getAllTrendingRepos()
        }

        if (localRepos.value.isNullOrEmpty()) {
            getReposFromApi(networkListener)
        } else {
            trendingRepos.value = localRepos.value
        }
    }

    private fun getReposFromApi(networkListener: NetworkListener?) {
        Api().getRepositories().enqueue(object : Callback<List<TrendingRepo>> {
            override fun onFailure(call: Call<List<TrendingRepo>>, t: Throwable) {
                networkListener?.onFailure()
            }

            override fun onResponse(
                call: Call<List<TrendingRepo>>,
                response: Response<List<TrendingRepo>>
            ) {
                if (response.isSuccessful) {
                    networkListener?.onSuccess()
                    trendingRepos.value = response.body()
                    insertToDb(response.body())
                } else {
                    networkListener?.onFailure()
                }
            }
        })
}

    private fun insertToDb(newRepos: List<TrendingRepo>?) {
        if (newRepos != null) {
            GlobalScope.launch {
                dao.insertRepos(newRepos)
            }
        }
    }

    fun sortByName() {
        trendingRepos.value = trendingRepos.value?.sortedBy { it.name }
    }

    fun sortByStars() {
        trendingRepos.value = trendingRepos.value?.sortedBy { it.stars }
    }
}
package com.learn.tdd.di

import android.app.Application
import com.learn.tdd.data.repository.RepoRepository
import com.learn.tdd.data.room.TrendingRepoDatabase

class Injection {

    companion object {
        fun provideRepository(application: Application): RepoRepository {
            val database = TrendingRepoDatabase(application)
            return RepoRepository(database.trendingRepoDao())
        }
    }

}
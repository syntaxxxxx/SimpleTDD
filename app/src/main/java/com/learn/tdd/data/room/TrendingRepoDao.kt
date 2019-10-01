package com.learn.tdd.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learn.tdd.data.entity.TrendingRepo

@Dao
interface TrendingRepoDao {

    @Query("SELECT * FROM trendingRepo")
    fun getAllTrendingRepos(): LiveData<List<TrendingRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: List<TrendingRepo>)
}
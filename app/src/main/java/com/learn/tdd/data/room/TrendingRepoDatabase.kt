package com.learn.tdd.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learn.tdd.data.entity.TrendingRepo

@Database(entities = [TrendingRepo::class], version = 1, exportSchema = false)
abstract class TrendingRepoDatabase : RoomDatabase() {

    abstract fun trendingRepoDao(): TrendingRepoDao

    companion object {
        @Volatile private var instance: TrendingRepoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            TrendingRepoDatabase::class.java, "trending-repo.db")
            .build()
    }

}
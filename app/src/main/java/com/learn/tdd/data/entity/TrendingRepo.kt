package com.learn.tdd.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trendingRepo")
data class TrendingRepo(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "forks") val forks: Int,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "languageColor") val languageColor: String?,
    @ColumnInfo(name = "stars") val stars: Int
)
package com.learn.tdd.data.network

import com.learn.tdd.BuildConfig
import com.learn.tdd.data.entity.TrendingRepo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("repositories")
    fun getRepositories(): Call<List<TrendingRepo>>

    companion object {
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}
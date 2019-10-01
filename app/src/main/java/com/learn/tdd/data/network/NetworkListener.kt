package com.learn.tdd.data.network

interface NetworkListener {
    fun onSuccess()
    fun onFailure()
}
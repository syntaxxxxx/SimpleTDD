package com.learn.tdd.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learn.tdd.data.repository.RepoRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @JvmField
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(RepoRepository())
    }

    @Test
    fun getNetworkListener() {
    }

    @Test
    fun setNetworkListener() {
    }

    @Test
    fun getRepos() {
        viewModel.getRepos()
    }

    @Test
    fun sortByName() {
        viewModel.sortByName()
    }

    @Test
    fun sortByStar() {
        viewModel.sortByStar()
    }
}
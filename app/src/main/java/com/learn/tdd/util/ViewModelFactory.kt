package com.learn.tdd.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.tdd.data.repository.RepoRepository
import com.learn.tdd.di.Injection
import com.learn.tdd.ui.main.MainViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null
        private val LOCK = Any()
        lateinit var repoRepository: RepoRepository

        operator fun invoke(application: Application) = instance ?: synchronized(LOCK) {
            instance ?: getInstance().also {
                instance = it
                repoRepository = Injection.provideRepository(application)
            }
        }

        private fun getInstance() = ViewModelFactory()

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repoRepository) as T
    }
}
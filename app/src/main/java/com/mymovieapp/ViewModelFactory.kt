/**
 * Copyright (C) Vijay Kumar Yadav.
 */
package com.mymovieapp

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.mymovieapp.viewmodels.MovieViewModel
import com.mymovieapp.viewmodels.Repo
import example.com.mvvmmoviedb.di.Injection

/**
 * Factory class to create View Models.
 */
class ViewModelFactory private constructor(
        private val application: Application,
        private val repository: Repo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MovieViewModel::class.java) ->
                        MovieViewModel(application, repository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(application,
                            Injection.provideTasksRepository(application.applicationContext))
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

package com.mymovieapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import com.mymovieapp.adapter.MovieAdapter
import com.mymovieapp.models.ResultMovie
import com.mymovieapp.models.retrofit.MovieSource
import com.mymovieapp.room.Results

class MovieViewModel(
        context: Application,
        private val repository: Repo
) : AndroidViewModel(context) {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:items")
        fun entries(recyclerView: RecyclerView, movies: List<Results>) =
                (recyclerView.adapter as MovieAdapter).addToList(movies)
    }

    val dataLoading = ObservableBoolean(false)
    val items: ObservableList<Results> = ObservableArrayList()
    internal val openMovieDetailsEvent = SingleLiveEvent<Results>()

    fun loadMovies(index: Int) {
        dataLoading.set(true)
        repository.getMovies(index,object :MovieSource.MoviesCallback{
            override fun onFailure(t: Throwable?) {

            }

            override fun onSuccess(movies: ResultMovie) {
                with(items) {
                    addAll(movies.results)
                }
            }

        })
    }
}
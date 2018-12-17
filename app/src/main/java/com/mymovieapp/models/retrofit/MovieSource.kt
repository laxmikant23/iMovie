package com.mymovieapp.models.retrofit

import com.mymovieapp.models.ResultMovie
import com.mymovieapp.room.Results

interface MovieSource {

    interface MoviesCallback {

        fun onSuccess(movies: ResultMovie)

        fun onFailure(t: Throwable? = Throwable())
    }

    fun getMovies(page: Int = 1, callback: MoviesCallback)
}
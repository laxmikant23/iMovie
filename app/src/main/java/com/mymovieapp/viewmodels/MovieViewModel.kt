package com.mymovieapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import com.mymovieapp.Utility
import com.mymovieapp.models.ResultMovie
import com.mymovieapp.models.retrofit.RetroFitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private var popularMovies = MutableLiveData<ResultMovie>()
    init {

             popularMovies = MutableLiveData<ResultMovie>()

    }

    fun loadPopularMovies(pageNum : Int) : LiveData<ResultMovie>? {
        var callResponse = RetroFitClient.create()
        val request = callResponse!!.getPopularMovies(Utility.API_KEY,pageNum)
        request.enqueue(object : Callback<ResultMovie> {
            override fun onResponse(call: Call<ResultMovie>?, response: Response<ResultMovie>?) {
                popularMovies.value = response?.body()
            }

            override fun onFailure(call: Call<ResultMovie>?, t: Throwable?) {

            }
        })
        return popularMovies
    }
}
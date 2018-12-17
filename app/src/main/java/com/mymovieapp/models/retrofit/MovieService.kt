package com.mymovieapp.models.retrofit

import com.mymovieapp.models.ResultMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService{
    @GET("?sort_by=popularity.desc")
    fun getPopularMovies(@Query("page") page:Int,@Query("api_key")api_key: String): Call<ResultMovie>
    //api_key=8d75ab3ab6fee1e4b1aecf6c5210308c&page=1
}
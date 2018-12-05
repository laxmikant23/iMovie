package com.mymovieapp.models.retrofit

import com.mymovieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

class RetroFitClient{
    companion object {
        fun create(): MovieService? {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/discover/movie/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(MovieService::class.java)
        }
    }
}
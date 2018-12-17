/**
 * Copyright (C) Vijay Kumar Yadav.
 */
package example.com.mvvmmoviedb.di

import android.content.Context
import com.mymovieapp.models.retrofit.MovieService
import com.mymovieapp.models.retrofit.RetroFitClient
import com.mymovieapp.models.retrofit.RetroFitClient.Companion.BASE_URL
import com.mymovieapp.viewmodels.Repo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Inject the dependency to create Repository. Useful for testing.
 */
object Injection {

    fun provideTasksRepository(context: Context): Repo {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/discover/movie/")

                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(MovieService::class.java)
        return Repo.getInstance(RetroFitClient.getInstance(apiService))
    }
}

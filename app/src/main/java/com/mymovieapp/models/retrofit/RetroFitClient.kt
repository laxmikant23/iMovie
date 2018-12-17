package com.mymovieapp.models.retrofit

import com.mymovieapp.Utility
import com.mymovieapp.models.ResultMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroFitClient (val apiService: MovieService) : MovieSource{
    override fun getMovies(page: Int,  callback: MovieSource.MoviesCallback) {
        val queryMap = HashMap<String, String>()

        queryMap[PAGE] = page.toString()
        val call = apiService.getPopularMovies(page,Utility.API_KEY)
        call.enqueue(object : Callback<ResultMovie> {
            override fun onFailure(call: Call<ResultMovie>?, t: Throwable?) {
                var error = t!!.localizedMessage
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<ResultMovie>?, response: Response<ResultMovie>?) {
                response?.run {
                    if (isSuccessful) {
                        callback.onSuccess(response.body()!!)
                    } else {
                        callback.onFailure()
                    }
                }
            }
        });
    }

    companion object {

        internal const val BASE_URL = "https://api.themoviedb.org/3/discover/movie"
        private const val PAGE = "page"
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
        internal const val SMALL_SIZE_URL = BASE_IMAGE_URL + "w185/"
        internal const val MID_SIZE_URL = BASE_IMAGE_URL + "w3 42/"


        private var sINSTANCE: RetroFitClient? = null


        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance(apiService: MovieService) =
                sINSTANCE ?: synchronized(RetroFitClient::class.java) {
                    sINSTANCE ?: RetroFitClient(apiService)
                            .also { sINSTANCE = it }
                }


        @JvmStatic
        fun destroyInstance() {
            sINSTANCE = null
        }
    }


   /* override fun getMovies(page: Int, withReleaseDate: Boolean, callback: MovieSource.MoviesCallback) {
        val queryMap = HashMap<String, String>()

        queryMap[PAGE] = page.toString()
        val call = apiService.getMovies(queryMap);
        call.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                callback.onOperationFailed(t);
            }

            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                response?.run {
                    if (isSuccessful) {
                        callback.onOperationComplete(response.body())
                    } else {
                        callback.onOperationFailed()
                    }
                }
            }
        });
    }*/
}
package com.mymovieapp.viewmodels

import com.mymovieapp.models.retrofit.MovieSource


class Repo(private val remoteDataSource: MovieSource) : MovieSource {

    companion object {

        private var sINSTANCE: Repo? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param remoteDataSource the backend data source
         *
         * @return the [Repository] instance
         */
        @JvmStatic
        fun getInstance(remoteDataSource: MovieSource) =
                sINSTANCE ?: synchronized(Repo::class.java) {
                    sINSTANCE ?: Repo(remoteDataSource)
                            .also { sINSTANCE = it }
                }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            sINSTANCE = null
        }
    }

    override fun getMovies(page: Int, callback: MovieSource.MoviesCallback) {
        remoteDataSource.getMovies(page, callback)
    }
}

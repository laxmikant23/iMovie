package com.mymovieapp.room

import android.arch.persistence.room.*

@Dao
interface MovieDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<Results>)

    @Update
    fun updateMovie(movie: Results)

   /* @Query("SELECT * FROM MyMovie where id = :id_")
    fun getMovieBasedonID(id_ : Int): Results

    @Query("SELECT * FROM MyMovie WHERE page = :page_")
    fun getMovieList(page_ : Int)*/
}
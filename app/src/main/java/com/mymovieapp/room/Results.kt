package com.mymovieapp.room

import android.databinding.BaseObservable

data class Results(
        var vote_count: Int,
        var id: Int,
        var video: Boolean,
        var vote_average: Float,
        val title: String?,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: ArrayList<Int>,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String?): BaseObservable()

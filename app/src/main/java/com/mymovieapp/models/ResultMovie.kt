package com.mymovieapp.models

import com.mymovieapp.room.Results


data class  ResultMovie( var results  : ArrayList<Results> ,
                         var page: Int? , var total_pages: Int ,
                         var total_results: Int)
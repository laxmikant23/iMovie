package com.mymovieapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mymovieapp.models.ResultMovie
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.mymovieapp.Utils.InfiniteScrollListner
import com.mymovieapp.adapter.MovieAdapter
import com.mymovieapp.room.Results
import com.mymovieapp.viewmodels.MovieViewModel


class MainActivity : AppCompatActivity() {
    private var list : MutableList<Results> = ArrayList()
    private var movieAdapter : MovieAdapter ?= null
    private var recyclerView : RecyclerView ?= null
    private var gridLayout:GridLayoutManager ?= null
    private var model:MovieViewModel ?= null
    private var pageNo = 1
    private lateinit var infiniteScrollListener: InfiniteScrollListner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerView)
        gridLayout =  GridLayoutManager(this@MainActivity,2)

        setupListAdapter()
        fetchItems(pageNo)
    }

    fun fetchItems(page:Int){
        model!!.loadPopularMovies(page)!!.observe(this, object: LiveData<ResultMovie>(), Observer<ResultMovie> {
            override fun onChanged(resultMovie: ResultMovie?) {
                list.addAll( resultMovie!!.results)
                movieAdapter!!.setMovieList(list)
               // recyclerView!!.scrollToPosition(list.size-resultMovie!!.results.size)
            }

        })
    }
    fun setupListAdapter(){
        recyclerView!!.layoutManager = gridLayout
        movieAdapter  = MovieAdapter(list)
        recyclerView!!.adapter = movieAdapter
        infiniteScrollListener = object : InfiniteScrollListner(gridLayout,pageNo){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                fetchItems(page)
            }

        }
        recyclerView!!.addOnScrollListener(infiniteScrollListener)
    }
}

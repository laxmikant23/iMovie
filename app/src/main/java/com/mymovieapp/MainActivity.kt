package com.mymovieapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.mymovieapp.Utils.InfiniteScrollListner
import com.mymovieapp.Utils.obtainViewModel
import com.mymovieapp.adapter.MovieAdapter
import com.mymovieapp.databinding.ActivityMainBinding
import com.mymovieapp.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {
    private var listAdapter : MovieAdapter ?= null
    private var recyclerView : RecyclerView ?= null
    private var pageNo = 1
    private lateinit var infiniteScrollListener: InfiniteScrollListner
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding  = DataBindingUtil.setContentView(this,R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        binding.viewmodel = obtainViewModel()


        setupListAdapter()
        fetchItems(pageNo)
    }

    fun fetchItems(page:Int){
        binding.viewmodel!!.loadMovies(page)
    }
    private fun setupListAdapter() {

        listAdapter = MovieAdapter(ArrayList(0), binding.viewmodel!!)
        with(recyclerView) {
            val gridLayout = GridLayoutManager(this@MainActivity, 2)

            this!!.setLayoutManager(gridLayout)
            infiniteScrollListener = object : InfiniteScrollListner(gridLayout, pageNo) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    binding.viewmodel?.loadMovies(page)
                }
            }
            addOnScrollListener(infiniteScrollListener)
            adapter = listAdapter
        }

    }
    fun obtainViewModel(): MovieViewModel = obtainViewModel(MovieViewModel::class.java)

}

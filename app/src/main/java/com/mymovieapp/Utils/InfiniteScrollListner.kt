package com.mymovieapp.Utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

abstract class InfiniteScrollListner(val layoutManager: GridLayoutManager?, val pageNo: Int):
        RecyclerView.OnScrollListener(){
    private var currentPage  = 1
    private var prevCount  = 0
    private var loading = true
    private var threashHold = 20


    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager!!.itemCount
        lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (totalItemCount < prevCount) {
            this.currentPage = this.pageNo
            this.prevCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        if (loading && totalItemCount > prevCount) {
            loading = false
            prevCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + threashHold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)

}
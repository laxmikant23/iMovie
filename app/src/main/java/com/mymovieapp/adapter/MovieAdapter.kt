package com.mymovieapp.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import com.mymovieapp.databinding.MovieItemBinding
import com.mymovieapp.room.Results
import com.mymovieapp.viewmodels.MovieViewModel

class MovieAdapter(
        private var movies: MutableList<Results>,
        private val moviesViewModel: MovieViewModel
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.run {
            bind(item)
            binding.root.setOnClickListener({ moviesViewModel.openMovieDetailsEvent.value = item })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val itemBinding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = movies.size



    fun addToList(movies: List<Results>) {
        val prevCount = itemCount
        this.movies.clear()
        this.movies.addAll(movies)
        if (prevCount > movies.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(prevCount, movies.size)
        }
    }

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results) {
            with(binding){
                data = item
                executePendingBindings()
            }

        }
    }
}


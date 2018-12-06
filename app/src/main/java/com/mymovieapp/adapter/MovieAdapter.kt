package com.mymovieapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import com.mymovieapp.BR
import com.mymovieapp.R
import com.mymovieapp.databinding.MovieItemBinding
import com.mymovieapp.room.Results
class MovieAdapter(private var movieList: MutableList<Results>): RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false)

        return WeatherViewHolder(binding)
    }
    fun setMovieList(movies : List<Results>){
        val prevCount = itemCount
       // movieList.clear()
        movieList.addAll(movies)
        //notifyDataSetChanged()

        if (prevCount > movies.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(prevCount, movieList.size)
        }
    }
    override fun getItemCount(): Int = movieList.size
}

class WeatherViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Any) {
        binding.setVariable(BR.data, data)
        binding.executePendingBindings()
    }
}

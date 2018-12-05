package com.mymovieapp.adapter

import com.squareup.picasso.Picasso
import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.mymovieapp.R
import com.mymovieapp.Utility


object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, url: String) {
        Log.e("IMAGE_URL ", Utility.IMAGE_BASE_URL+url)
        Picasso.with(imageView.context).load(Utility.IMAGE_BASE_URL+url).fit().centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
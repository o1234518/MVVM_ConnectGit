package com.example.mvvm_connectgit.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_connectgit.R

class RepoBindings {
    //BindingAdapter內的function都須為Static Function
    companion object {
        @BindingAdapter("visibleGone")
        @JvmStatic
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun bindImg(imageView: ImageView, url: String) {
            var context = imageView.context

            Glide.with(context)
                .load(url)
                .into(imageView)
        }
    }
}
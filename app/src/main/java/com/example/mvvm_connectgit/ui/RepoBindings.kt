package com.example.mvvm_connectgit.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RepoBindings {
    //BindingAdapter內的function都須為Static Function
    companion object {
        @BindingAdapter("visibleGone")
        @JvmStatic
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        //    @BindingAdapter("imageUrl")
//    fun bindImg(imageView: ImageView, url: String) {
//        var context = imageView.context
//        Glide.with(context)
//            .load(url)
//            .into(imageView)
//    }

        //當有多個參數時，可以直接這樣使用  預設自動載入holder的圖片，當imgUrl的資料進來時就換
        @BindingAdapter("imageUrl", "holder")
        @JvmStatic
        fun bindImg(imageView: ImageView, url: String, holder: Drawable) {
            var context = imageView.context
            var requestOptions = RequestOptions()
            requestOptions.placeholder(holder)

            Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(imageView)
        }
    }
}
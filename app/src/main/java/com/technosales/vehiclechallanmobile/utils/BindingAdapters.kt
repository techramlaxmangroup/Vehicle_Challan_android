package com.technosales.vehiclechallanmobile.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.technosales.vehiclechallanmobile.R
import timber.log.Timber

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibility")
    fun setVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageSrc")
    fun imageSrc(view: ImageView, imageUrl: String?) {
        val fullImageUrl = TextConfig.IMAGE_BASE_URL + imageUrl

        Timber.i("imageSrc (line 23): $fullImageUrl")
        val requestOptions: RequestOptions =
            RequestOptions().centerCrop().error(R.drawable.no_image)
        Glide.with(view.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(fullImageUrl)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("imageSrcCenterInside")
    fun imageSrcCenterInside(view: ImageView, imageUrl: String?) {
        val fullImageUrl = TextConfig.IMAGE_BASE_URL + imageUrl

        Timber.i("imageSrc (line 23): $fullImageUrl")
        val requestOptions: RequestOptions =
            RequestOptions().centerInside().error(R.drawable.no_image)
        Glide.with(view.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(fullImageUrl)
            .into(view)
    }

}

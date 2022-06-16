package com.example.listofimages.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.example.listofimages.R

@BindingAdapter("imageUrl")
fun setImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        imageView.load(url) {
            crossfade(true)
            error(R.drawable.ic_baseline_broken_image_24)
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.ENABLED)
        }
    } else {
        imageView.load(R.drawable.ic_baseline_broken_image_24) {
            crossfade(true)
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
        }
    }
}

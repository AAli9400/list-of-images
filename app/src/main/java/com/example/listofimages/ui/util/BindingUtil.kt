package com.example.listofimages.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.listofimages.R

@BindingAdapter("imageUrl")
fun setImage(image: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(image.context)
            .load(url)
            .transition(withCrossFade())
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(image)
    } else {
        Glide.with(image.context)
            .load(R.drawable.ic_baseline_broken_image_24)
            .transition(withCrossFade())
            .into(image)
    }
}

package io.interview.productlisttest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    load(url)
}
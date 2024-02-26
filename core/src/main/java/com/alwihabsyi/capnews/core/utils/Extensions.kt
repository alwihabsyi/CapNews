package com.alwihabsyi.capnews.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.glide(url: String) {
    Glide.with(this.context).load(url).into(this)
}
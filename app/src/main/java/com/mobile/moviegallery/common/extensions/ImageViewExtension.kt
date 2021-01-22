package com.mobile.moviegallery.common.extensions

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.mobile.moviegallery.common.ImageLoader

/**
 * Created by Ramiz Raja on 28/05/2020
 */
@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    ImageLoader.instance?.load(context, url ?: "", this)
}

fun ImageView.loadWithPlaceholder(url: String?, @DrawableRes placeholder: Int) {
    ImageLoader.instance?.load(context, url ?: "", this, placeholder)
}

@BindingAdapter("loadResource")
fun ImageView.load(@DrawableRes resource: Int) {
    ImageLoader.instance?.load(context, resource, this)
}

@BindingAdapter("load")
fun ImageView.load(drawable: Drawable) {
    ImageLoader.instance?.load(context, drawable, this)
}

@BindingAdapter("loadColor")
fun View.loadColor(color: String) {
    val background = this.background as GradientDrawable
    background.setColor(Color.parseColor(color))
//    this.setBackgroundColor(Color.parseColor("#686868"))
}
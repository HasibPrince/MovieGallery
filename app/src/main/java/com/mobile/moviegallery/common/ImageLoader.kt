package com.mobile.moviegallery.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import java.io.File


class ImageLoader {

    fun load(
        context: Context,
        url: String,
        imageView: ImageView,
        shimmerFrameLayout: ShimmerFrameLayout
    ) {
        shimmerFrameLayout.startShimmerAnimation()
        Glide.with(context)
            .load(url)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    shimmerFrameLayout.stopShimmerAnimation()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    shimmerFrameLayout.stopShimmerAnimation()
                    return false
                }

            })
            .fitCenter()
            .into(imageView)
    }

    fun load(
        context: Context,
        url: String,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(url)
            .fitCenter()
            .into(imageView)
    }

    fun load(
        context: Context,
        url: String,
        imageView: ImageView,
        placeholder: Int
    ) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .into(imageView)
    }

    fun load(
        context: Context,
        bitmap: Bitmap,
        imageView: ImageView,
        placeholder: Int
    ) {
        Glide.with(context)
            .load(bitmap)
            .placeholder(placeholder)
            .into(imageView)
    }

    fun load(
        context: Context,
        drawable: Int,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(drawable)
            .fitCenter()
            .into(imageView)
    }

    fun load(
        context: Context,
        drawable: Drawable,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(drawable)
            .into(imageView)
    }

    fun load(context: Context, file: File, skipCache: Boolean, imageView: ImageView) {
        Glide.with(context)
            .load(file)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(skipCache)
            .into(imageView)
    }

    fun loadGif(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .asGif()
            .load(url)
            .into(imageView)

    }

    companion object {
        protected var INSTANCE: ImageLoader? = null
        val instance: ImageLoader?
            get() {
                if (INSTANCE == null) INSTANCE =
                    ImageLoader()
                return INSTANCE
            }
    }
}
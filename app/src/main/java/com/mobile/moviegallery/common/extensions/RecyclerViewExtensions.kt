package com.mobile.moviegallery.common.extensions

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moviegallery.data.model.Movie

@BindingAdapter("dresses")
fun RecyclerView.setDresses(pagingData: PagingData<Movie>?) {
    pagingData?.let {
//        (adapter as MovieAdapter).submitData(it)
    }
}
package com.mobile.moviegallery.ui.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moviegallery.R

class ItemDecoration(private val bottomSpace: Int, private val leftSpace: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val childAdapterPosition = parent.getChildAdapterPosition(view)
        val spanCount = view.context.resources.getInteger(R.integer.span_count)
        if (childAdapterPosition < spanCount) {
            outRect.top = bottomSpace / 2
        }
        outRect.bottom = bottomSpace
        outRect.left = leftSpace
    }
}
package com.mk8.pruebaloycus.extension

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mk8.pruebaloycus.screen.base.BaseRecyclerView

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun RecyclerView.initVerticalRecycler(adapter: BaseRecyclerView<*, *>, isGridLayout: Boolean = false) = run {
    layoutManager = if (isGridLayout) GridLayoutManager(context, 2) else LinearLayoutManager(context)
    setHasFixedSize(true)
    this.adapter = adapter
}

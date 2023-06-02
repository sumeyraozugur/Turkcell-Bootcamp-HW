package com.sumeyra.centralbank.extension

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun setViewsGone(vararg views: View) {
    views.forEach {
        it.gone()
    }
}

fun setViewsVisible(vararg views: View) {
    views.forEach {
        it.visible()
    }
}
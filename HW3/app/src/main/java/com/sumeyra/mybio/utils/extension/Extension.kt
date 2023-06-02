package com.sumeyra.mybio.utils.extension

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sumeyra.mybio.R

fun Navigation.sent(v: View, id: Int) = findNavController(v).navigate(id)

fun Navigation.back(v: View) = findNavController(v).navigateUp()

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_INDEFINITE) {
    val snack = Snackbar.make(this, message, duration)
    snack.setAction(context.getString(R.string.dismiss)) {}
    val snackBarView = snack.view
    snackBarView.setBackgroundColor(
        ContextCompat.getColor(
            context,
            R.color.color_snackbar
        )
    )
    snack.show()
}



